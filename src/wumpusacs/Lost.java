/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wumpusacs;

import java.util.ArrayList;

/**
 *
 * @author elixandre
 */
public class Lost {
    int geration;
    double score;
    int qtdGold;
    int qtdMovimentos;
    ArrayList<Cell> path;
    
    public Lost(int g, double s, int q, int m, ArrayList<Cell> p) {
        this.geration = g;
        this.score = s;
        this.qtdGold = q;
        this.qtdMovimentos = m;
        this.path = p;
    }
    
    public void print() {
        System.out.println("Geration: "+geration);
        System.out.println("Score: "+score);
        System.out.println("qtdGold: "+qtdGold);
        System.out.println("qtdMovimentos: "+qtdMovimentos);
        System.out.println("Sem vencedores");
    }
}
