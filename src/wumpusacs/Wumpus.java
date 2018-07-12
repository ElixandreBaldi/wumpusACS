/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wumpusacs;

/**
 *
 * @author administrador
 */
public class Wumpus {
    private int line;

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    private int column;
    
    Wumpus(int line, int column) {
        this.line = line;
        this.column = column;
    }
    
}
