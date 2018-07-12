/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wumpusacs;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static wumpusacs.WumpusACS.MASKGOLD;
import static wumpusacs.WumpusACS.MASKUNKNOWN;
import static wumpusacs.WumpusACS.random;


/**
 *
 * @author administrador
 */
public class Agent {
    public int contMoviment;
    
    private int line;
    
    private int column;
    
    private int n;                        
    
    private ArrayList<Cell> path;
    
    private int score;
    
    Agent(int line, int column, int n) {
        this.contMoviment = 0;                        
        this.n = n;
        this.score = n*n;
        this.line = line;
        this.column = column;    
        this.path = new ArrayList<>();
        this.inicializeEnvironment();                       
        
    }
    
    public void inicializeEnvironment() {
        Cell cell = new Cell(0, 0, 0); //linha, coluna, feromonio que nao vai mudar        
        this.path.add(cell);
    }
    
    boolean isKnown(int line, int column) {        
        for(int i = 0; i < path.size(); i++) {            
            if(path.get(i).getI() == line && path.get(i).getJ() == column) return true;
        }
        return false;
    }
    
    public boolean moveTop() {        
        if(line > 0 && !isKnown(line-1,column)){                                    
            this.line--;
            this.path.add(new Cell(line, column, 0));     
            return true;
        }        
        
        return false;
    }
    
    public boolean moveBottom() {        
        if(line < n-1 && !isKnown(line+1,column)){                        
            this.line++;
            this.path.add(new Cell(line, column, 0));     
            return true;
        }        
        
        return false;
    }
    
    public boolean moveLeft() {
        if(column > 0 && !isKnown(line,column-1)){                        
            this.column--;
            this.path.add(new Cell(line, column, 0));     
            return true;
        }        
        
        return false;
    }
    
    public boolean moveRight() {
        if(column < n-1 && !isKnown(line,column+1)){                        
            this.column++;
            this.path.add(new Cell(line, column, 0));     
            return true;
        }        
        
        return false;
    }             
    
    public int getLine() {        
        return this.line;
    }
    
    public int getColumn() {        
        return this.column;
    }   
    
    public void action() {
        this.contMoviment++;
        System.out.println("Movimento: "+contMoviment);                
        movementRondom();        
        this.printKnown();        
    }               
    
    void printKnown() {
        for(int i = 0; i < path.size(); i++) {
            path.get(i).print();
        }
    }
    
    public void movementRondom(){            
        while(true) {
            int i = random.nextInt(4);
            if(i == 0 && this.moveTop()) {
                return;
            } else if(i == 1 && this.moveRight()){
                return;
            } else if(i == 2 && this.moveBottom()){
                return;
            } else if(i == 3 && this.moveLeft()){
                return;
            } else System.out.println("morte");            
        }                
    }        
    
    public int randomMovement(boolean positions[]) {        
        while(true) {
            int i = random.nextInt(4);
            if(positions[i])
                return i;
        }
    }
    
    void setGold() {
        this.score += 20;
        int size = this.path.size();        
        this.path.get(size-1).setInfo(MASKGOLD);
    }
    
    void setExit() {
        this.score += 10;
    }
    
    void setDead() {
        this.score -= 50;
    }
}
