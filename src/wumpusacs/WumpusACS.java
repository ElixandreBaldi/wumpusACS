/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wumpusacs;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author elixandre
 */
public class WumpusACS {

    public static int n = 15;
    
    public static int nPopulation = 20;
    
    public static int nGeration = 100;
    
    public static int rateFeromonio = 1;
    
    public static int rateEvaporacao = 1;
    
    public static float alfa = 1;
    
    public static float beta = 1;
    
    private Cell board[][];

    public static Agent a[];

    public static Environment t;

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
    public static void main(String[] args) {
        t = new Environment(n);
        a = new Agent[nPopulation];
        double scoreGlobal = 0;
        for(int j = 0; j < nGeration; j++) {
            for(int i = 0; i < nPopulation; i++) {
                a[i] = new Agent(0, 0, n);
                while (true) {
                    if(!a[i].action()) {
                        System.out.println("Formiga Presa");
                        break;
                    }            
                    if(!(verifyFuture(a[i].getLine(), a[i].getColumn(), i, j))) break;
                    //printMatrix();                
                }
                if(scoreGlobal < a[i].score) scoreGlobal = a[i].score;            
                t.evaporar(rateEvaporacao);
                depositar(i, scoreGlobal);
                System.out.println("");
                System.out.println("Formiga: "+i);
                printMatrix();
                System.out.println("");
            }   
            System.out.println("");
            System.out.println("");
            System.out.println("");
        }
    }   
    
    
    public static void printMatrix() {        
        //t.printBoard();
        t.printBoardFeromonio();
    }
    
    
    public static boolean verifyFuture(int line, int column, int indexAgent, int indexGeration) {                 
        short sensation = t.getSensation(line, column);
        
        a[indexAgent].setFeromonioLeft(t.getFeromonio(line, column-1));
        a[indexAgent].setFeromonioRight(t.getFeromonio(line, column+1));
        a[indexAgent].setFeromonioTop(t.getFeromonio(line-1, column));
        a[indexAgent].setFeromonioBottom(t.getFeromonio(line+1, column));
        
        if ((sensation & MASKWUMPUS) != 0 || (sensation & MASKHOLE) != 0) { // morreu
            System.out.println("You lost");            
            a[indexAgent].setDead();
            return false;
        } else if ((sensation & MASKGOLD) != 0) { // ganhou
            System.out.println("You find a gold! ");            
            a[indexAgent].setGold();            
        } else if ((sensation & MASKEXIT) != 0) { // ganhou
            System.out.println("Congratilations! You Winn! "+a[indexAgent].contMoviment +" movimentos!");            
            System.out.println("Score: "+a[indexAgent].score);
            System.out.println("Geration: "+indexGeration);
            a[indexAgent].setExit();                  
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
            t.deposito(line, column, deposito);
        }
    }    
}
