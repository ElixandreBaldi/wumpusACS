/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wumpusacs;

import java.util.Arrays;
import java.util.Random;
import static wumpusacs.WumpusACS.MASKEXIT;
import static wumpusacs.WumpusACS.MASKGOLD;
import static wumpusacs.WumpusACS.MASKHOLE;
import static wumpusacs.WumpusACS.MASKWUMPUS;
import static wumpusacs.WumpusACS.n;
import static wumpusacs.WumpusACS.random;

/**
 *
 * @author administrador
 */
public class Environment {      

    private Hole holes[];    

    private Gold golds[];    

    private Wumpus wumpus;

    private Agent agent;    

    private int numberHoles;

    private int numberGolds;

    private int n;   
    
    private Cell[][] board;

    public Environment(int n) {
        this.init(n);

        this.setWumpus();
        
        this.setHoles(n / 2);
        
        this.setGolds(n);
        
        this.setExit();
    }       
    
    public void init(int n) {
        this.n = n;
        
        board = new Cell[n][n];                
        
        this.inicializeBoard();               
    }
    
    public double getFeromonio(int i, int j) {
        if(i < 0 || j < 0 || i >= n || j>= n) return 0;
        return this.board[i][j].getFeromonio();
    }

    public void inicializeBoard() {
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {                
                this.board[i][j] = new Cell(i, j, 0);
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                byte info = this.board[i][j].getInfo();                
                if((info & 64) == 64) info = (byte) (info & 64);
                System.out.printf("%4d",info);
            }
            System.out.println();
        }
    }

    public int generateNumberRandom(int min, int max) {        
        int number = random.nextInt(this.n);
        if (number < min) {
            return min;
        } else if (number > max) {
            return max;
        }
        return number;
    }

    public void setWumpus() {
        int line = generateNumberRandom(0, this.n - 1);
        int column = generateNumberRandom(0, this.n - 1);
        if (line == 0 && column == 0) {
            line = this.n - 1;
            column = this.n - 1;
        }
        this.wumpus = new Wumpus(line, column);
        this.board[line][column].setInfo(MASKWUMPUS);        
    }
    
    public void setExit() {
        int line, column;
        while (true) {
            line = generateNumberRandom(0, this.n - 1);
            column = generateNumberRandom(0, this.n - 1);
            if ((this.board[line][column].verifyAndMask(MASKWUMPUS)) == 0 && (this.board[line][column].verifyAndMask(MASKHOLE)) == 0 && (line != 0 || column != 0)) {
                break;
            }
        }        
        this.board[line][column].setInfo(MASKEXIT);        
    }

    public void setHoles(int nHoles) {
        this.holes = new Hole[nHoles];
        for (int i = 0; i < this.holes.length; i++) {
            int line, column;
            while (true) {
                line = generateNumberRandom(0, this.n - 1);
                column = generateNumberRandom(0, this.n - 1);
                if ((this.board[line][column].verifyAndMask(MASKWUMPUS)) == 0 && (this.board[line][column].verifyAndMask(MASKHOLE)) == 0 && (line != 0 || column != 0)) {
                    break;
                }
            }
            this.holes[i] = new Hole(line, column);
            this.board[line][column].setInfo(MASKHOLE);
        }
    }

    public void setGolds(int nGolds) {
        this.golds = new Gold[nGolds];
        for (int i = 0; i < this.golds.length; i++) {
            int line, column;

            while (true) {
                line = generateNumberRandom(0, this.n - 1);
                column = generateNumberRandom(0, this.n - 1);
                if ((this.board[line][column].verifyAndMask(MASKWUMPUS)) == 0 && (this.board[line][column].verifyAndMask(MASKHOLE)) == 0 && (line != 0 || column != 0)) {
                    break;
                }
            }
            this.golds[i] = new Gold(line, column);
            this.board[line][column].setInfo(MASKGOLD);    
        }
    }
    
    public byte getSensation(int i, int j){
        this.board[i][j].setInfo((byte)64);
        return this.board[i][j].getInfo();
    }
    
    public void evaporar(double taxa) {
        for(int i = 0; i < this.n; i++) {
            for(int j = 0; j < this.n; j++) {
                this.board[i][j].evaporar(taxa);
            }
        }
    }
    
    public void deposito(int i, int j, double deposito) {
        this.board[i][j].deposito(deposito);     
    }
}
