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
public class Win {
    int geration;
    double score;
    int qtdGold;
    int qtdMovimentos;
    
    public Win(int g, double s, int q, int m) {
        this.geration = g;
        this.score = s;
        this.qtdGold = q;
        this.qtdMovimentos = m;
    }
    
    public void print() {
        System.out.println("Geration: "+geration);
        System.out.println("Score: "+score);
        System.out.println("qtdGold: "+qtdGold);
        System.out.println("qtdMovimentos: "+qtdMovimentos);
    }
}
