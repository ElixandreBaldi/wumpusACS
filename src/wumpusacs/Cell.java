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
    private double feromonioTop;
    private double feromonioBottom;
    private double feromonioLeft;
    private double feromonioRight;
    int print;
    
    private int lado; //lado 1 = top, lado 2 = bottom, lado 3 = left, lado 4 = right Apenas para o agente
    
    Cell(int i, int j) {
        this.i = i;
        this.j = j;
        this.feromonioTop = 0.0;
        this.feromonioBottom = 0.0;
        this.feromonioLeft = 0.0;
        this.feromonioRight = 0.0;
        this.info = (byte) 0;        
    }
    
    Cell(int i, int j, double t, double b, double l, double r) {
        this.i = i;
        this.j = j;
        this.feromonioTop = t;
        this.feromonioBottom = b;
        this.feromonioLeft = l;
        this.feromonioRight = r;
        this.info = (byte) 0;        
    }
    
    Cell(int i, int j, int lado) {
        this.i = i;
        this.j = j;
        this.feromonioTop = 0.0;
        this.feromonioBottom = 0.0;
        this.feromonioLeft = 0.0;
        this.feromonioRight = 0.0;
        this.info = (byte) 0;  
        this.lado = lado;
    }
    
    Cell(int i, int j, int print, boolean t) {
        this.i = i;
        this.j = j;
        this.info = (byte) 128;
        this.print = print;
    }

    public int getLado() {
        return lado;
    }

    public void setLado(int lado) {
        this.lado = lado;
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

    public double getFeromonioTop() {
        return feromonioTop;
    }

    public void setFeromonioTop(double feromonio) {
        this.feromonioTop = feromonio;
    }     
    
    public double getFeromonioBottom() {
        return feromonioBottom;
    }

    public void setFeromonioBottom(double feromonio) {
        this.feromonioBottom = feromonio;
    }  
    
    public double getFeromonioRight() {
        return feromonioRight;
    }

    public void setFeromonioRight(double feromonio) {
        this.feromonioRight = feromonio;
    }  
    
    public double getFeromonioLeft() {
        return feromonioLeft;
    }

    public void setFeromonioLeft(double feromonio) {
        this.feromonioLeft = feromonio;
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
    
    void evaporar(double taxa) {
        this.feromonioTop *= taxa;
        this.feromonioBottom *= taxa;
        this.feromonioLeft *= taxa;
        this.feromonioRight *= taxa;
    }
    
    void depositoTop(double deposito) {
        this.feromonioTop += deposito;
    }
    
    void depositoBottom(double deposito) {
        this.feromonioBottom += deposito;
    }
    
    void depositoLeft(double deposito) {
        this.feromonioLeft += deposito;
    }
    
    void depositoRight(double deposito) {
        this.feromonioRight += deposito;
    }
}
