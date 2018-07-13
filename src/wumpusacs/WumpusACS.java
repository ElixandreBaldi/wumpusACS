/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wumpusacs;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author elixandre
 */
public class WumpusACS {

    public static int n;
    
    public static int nPopulation;
    
    public static int nGeration;
    
    public static double rateFeromonio; //máximo 1
    
    public static double rateEvaporacao;
    
    public static double alfa;     
    
    public static double rateDepositoGoldExitHoleWumpus;

    public static Agent a[];

    public static Environment t;
    
    public static ArrayList<Win> win;
    public static ArrayList<Lost> lost;

    public static final byte MASKANT = 1;

    public static final byte MASKWUMPUS = 2;

    public static final byte MASKHOLE = 4;

    public static final byte MASKGOLD = 8;

    public static final byte MASKUNKNOWN = 16;

    public static final byte MASKEXIT = 32;    

    public static Random random = new Random();
    
    static short buffer_env[][];

    /**
     * @param args the command line arguments
     */
    
    public static void getData() throws InterruptedException {
        CaixaDeTexto texfield = new CaixaDeTexto();		  
        texfield.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        texfield.setSize(400, 500);
        texfield.setVisible(true); 
        boolean flag = texfield.stop;
        while(texfield.stop) {            
            TimeUnit.SECONDS.sleep(1);
        }
        
        n = texfield.getN();    
        nPopulation = texfield.getnPopulation();
        nGeration = texfield.getnGeration();
        rateFeromonio = texfield.getRateFeromonio(); //máximo 1
        rateEvaporacao = texfield.getRateEvaporacao();
        alfa = texfield.getAlfa();     
        rateDepositoGoldExitHoleWumpus = texfield.getRateDepositoGoldExitHoleWumpus();
    }
    
    public static void main(String[] args) throws InterruptedException {
        getData();
              
        
        
        t = new Environment(n);
        a = new Agent[nPopulation];
        win = new ArrayList<Win>();
        lost = new ArrayList<Lost>();
        double scoreGlobal = 0;
        for(int j = 0; j < nGeration; j++) {
            for(int i = 0; i < nPopulation; i++) {
                a[i] = new Agent(0, 0, n);
                while (true) {
                    if(!a[i].action()) {
                        lost.add(new Lost(j, a[i].score, a[i].countGold, a[i].contMoviment));
                        break;
                    }            
                    if(!(verifyFuture(a[i].getLine(), a[i].getColumn(), i, j))) break;                       
                }
                if(scoreGlobal < a[i].score) scoreGlobal = a[i].score;                            
                //System.out.println("");
                //System.out.println("Formiga: "+i);
                //printMatrix();
                //System.out.println("");
            }   
            t.evaporar(rateEvaporacao);
            for(int i = 0; i < nPopulation; i++) depositar(i, scoreGlobal);            
        }
        printMatrix();
        printWin();
        printBest();
    }   
    
    public static void printBest() {
        int indexBest = -1;
        double score = 0;
        for(int i = 0; i < win.size(); i++) {
            if(win.get(i).score > score) {
                score = win.get(i).score;
                indexBest = i;
            }
        }
        
        if(indexBest == -1) {
            System.out.println("Sem vencedor");
            printBestLost();
            return;
        }        
        
        System.out.println("");
        System.out.println("");
        System.out.println("Best:");
        
        win.get(indexBest).print();
    }
    
    public static void printBestLost() {
        int indexBest = -1;
        double score = 0;
        for(int i = 0; i < lost.size(); i++) {
            if(lost.get(i).score > score) {
                score = lost.get(i).score;
                indexBest = i;
            }
        }
        System.out.println("");
        System.out.println("");
        System.out.println("Best:");
        
        lost.get(indexBest).print();
    }
    
    public static void printWin() {
        for(int i = 0; i < win.size(); i++){
            win.get(i).print();
            System.out.println("");
        }
    }
    
    public static void printMatrix() {        
        t.printBoard();
        //t.printBoardFeromonio();
    }
    
    
    public static boolean verifyFuture(int line, int column, int indexAgent, int indexGeration) {                 
        short sensation = t.getSensation(line, column);
        
        
        //lado 1 = top, lado 2 = bottom, lado 3 = left, lado 4 = right
        a[indexAgent].setFeromonioLeft(t.getFeromonio(line, column-1, 4));
        a[indexAgent].setFeromonioRight(t.getFeromonio(line, column+1, 3));
        a[indexAgent].setFeromonioTop(t.getFeromonio(line-1, column, 2));
        a[indexAgent].setFeromonioBottom(t.getFeromonio(line+1, column, 1));
        
        if ((sensation & MASKWUMPUS) != 0 || (sensation & MASKHOLE) != 0) { // morreu
            //System.out.println("You lost");            
            a[indexAgent].setDead();
            lost.add(new Lost(indexGeration, a[indexAgent].score, a[indexAgent].countGold, a[indexAgent].contMoviment));
            return false;
        } else if ((sensation & MASKGOLD) != 0) { // ganhou
            //System.out.println("You find a gold! ");            
            a[indexAgent].setGold();            
        } else if ((sensation & MASKEXIT) != 0) { // ganhou
            //System.out.println("Congratilations! You Winn! "+a[indexAgent].contMoviment +" movimentos!");            
            //System.out.println("Score: "+a[indexAgent].score);
            //System.out.println("Geration: "+indexGeration);
            a[indexAgent].setExit();
            win.add(new Win(indexGeration, a[indexAgent].score, a[indexAgent].countGold, a[indexAgent].contMoviment));
            return false;
        }        
        return true;
    }
    
    public static void depositar(int indexAgent, double scoreGlobal){
        ArrayList<Cell> path = a[indexAgent].path;
        double scoreLocal = a[indexAgent].score;
        double deposito = (scoreLocal / scoreGlobal) * rateFeromonio;
                
        
        for(int i = 0; i < path.size(); i++){
            int line = path.get(i).getI();
            int column = path.get(i).getJ();            
            
            if((path.get(i).getInfo() & MASKGOLD) == MASKGOLD) deposito += deposito * rateDepositoGoldExitHoleWumpus;
            else if((path.get(i).getInfo() & MASKEXIT) == MASKEXIT) deposito += deposito * rateDepositoGoldExitHoleWumpus;            
            else if((path.get(i).getInfo() & MASKHOLE) == MASKHOLE) deposito -= deposito * rateDepositoGoldExitHoleWumpus;
            else if((path.get(i).getInfo() & MASKWUMPUS) == MASKWUMPUS) deposito -= deposito * rateDepositoGoldExitHoleWumpus;
            
            int lado = path.get(i).getLado();
            t.deposito(line, column, deposito, lado);
        }
    }    
}
