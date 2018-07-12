/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wumpusacs;

/**
 *
 * @author elixandre
 */
public class Cell {
    private int i;
    private int j;
    private byte info;
    private float feromonio;
    
    Cell(int i, int j, float feromonio) {
        this.i = i;
        this.j = j;
        this.feromonio = feromonio;
        this.info = (byte) 0;
    }
    
    public void setInfo(byte mask) {
        this.info = (byte) (this.info | mask);
    }
    
    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }    

    public float getFeromonio() {
        return feromonio;
    }

    public void setFeromonio(float feromonio) {
        this.feromonio = feromonio;
    }        
    
    public byte verifyAndMask(byte mask) {
        return (byte) (mask & this.info);
    }
    
    public byte verifyOrMask(byte mask) {
        return (byte) (mask | this.info);
    }
    
    public byte getInfo() {
        return this.info;
    }
    
    void print() {
        System.out.println("["+i+", "+j+"]");
    }
}
