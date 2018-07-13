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
    
    public static int nPopulation = 10;
    
    public static int nGeration = 10;
    
    public static int rateFeromonio = 1;
    
    public static int rateEvaporacao = 1;
    
    public static float alfa = 1;
    
    public static float beta = 1;
    
    private Cell board[][];

    public static Agent a;

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
        a = new Agent(0, 0, n);
        while (true) {
            a.action();            
            verifyFuture(a.getLine(), a.getColumn());
            //printMatrix();
            t.evaporar(rateEvaporacao);
        }
    }   
    
    
    public static void printMatrix() {
        /*System.out.println("");        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%4d", buffer_env[i][j]);
            }
            System.out.println();
        }
        System.out.println("");
        System.out.println("");       */
        
        t.printBoard();
    }
    
    
    public static void verifyFuture(int line, int column) {                 
        short sensation = t.getSensation(line, column);
        
        a.setFeromonioLeft(t.getFeromonio(line, column-1));
        a.setFeromonioRight(t.getFeromonio(line, column+1));
        a.setFeromonioTop(t.getFeromonio(line-1, column));
        a.setFeromonioBottom(t.getFeromonio(line+1, column));
        
        if ((sensation & MASKWUMPUS) != 0 || (sensation & MASKHOLE) != 0) { // morreu
            System.out.println("You lost");
            printMatrix();
            a.setDead();
            depositar(a.score, a.path);
            
        } else if ((sensation & MASKGOLD) != 0) { // ganhou
            System.out.println("You find a gold! ");            
            a.setGold();            
        } else if ((sensation & MASKEXIT) != 0) { // ganhou
            System.out.println("Congratilations! You Winn! "+a.contMoviment +" movimentos!");
            printMatrix();
            a.setExit();
            depositar(a.score, a.path);         
        }
        printMatrix();
    }
    
    public static void depositar(int scoreLocal, ArrayList<Cell> path){
        int scoreTotal = 500;
        double deposito = (scoreLocal / scoreTotal) * rateFeromonio;
        
        for(int i = 0; i < path.size(); i++){
            int line = path.get(i).getI();
            int column = path.get(i).getJ();
            
            t.deposito(line, column, deposito);
        }
    }
    
}
