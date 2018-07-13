/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wumpusacs;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
/**
 *
 * @author elixandre
 */
public class CaixaDeTexto extends JFrame{    
    private JButton iniciar, padrao;
    private JLabel dimensao, geracao, populacao, mediaFeromonio, mediaEvaporacao, alfas, mediaDeposito, info, quebra;
    private JTextField dimensao2, geracao2, populacao2, mediaFeromonio2, mediaEvaporacao2, alfas2, mediaDeposito2, info2;
    
    public boolean stop = true;
    private int n;
    
    private int nPopulation;
    
    private int nGeration;
    
    private double rateFeromonio; //máximo 1
    
    private double rateEvaporacao;
    
    private double alfa;     
    
    private double rateDepositoGoldExitHoleWumpus;

    public CaixaDeTexto(){
        super("Mundo do Wumpus");
        setLayout(new FlowLayout());                        
        
        dimensao = new JLabel("Dimensão do Labirinto: ");
        add(dimensao);

        dimensao2 = new JTextField(30);
        add(dimensao2);

        geracao = new JLabel("Quantidade de Gerações: ");
        add(geracao);

        geracao2 = new JTextField(30);
        add(geracao2); 

        populacao = new JLabel("Quantidade de Formigas por geração: ");
        add(populacao);

        populacao2 = new JTextField(30);
        add(populacao2); 

        mediaFeromonio = new JLabel("Taxa do Depósito de Feromônio: ");
        add(mediaFeromonio);

        mediaFeromonio2 = new JTextField(30);
        add(mediaFeromonio2); 

        mediaEvaporacao = new JLabel("Taxa do Evaporação de Feromônio: ");
        add(mediaEvaporacao);

        mediaEvaporacao2 = new JTextField(30);
        add(mediaEvaporacao2); 

        alfas = new JLabel("Valor de alfa: ");
        add(alfas);

        alfas2 = new JTextField(30);
        add(alfas2); 

        mediaDeposito = new JLabel("Taxa de depósitos especiais: ");
        add(mediaDeposito);

        mediaDeposito2 = new JTextField(30);
        add(mediaDeposito2);



        iniciar = new JButton("Iniciar");
        iniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento){
                if(evento.getSource() == iniciar) {
                    n = Integer.parseInt(dimensao2.getText());
                    nPopulation = Integer.parseInt(populacao2.getText());
                    nGeration = Integer.parseInt(geracao2.getText());
                    rateFeromonio = Double.parseDouble(mediaFeromonio2.getText());
                    rateEvaporacao = Double.parseDouble(mediaEvaporacao2.getText());
                    alfa = Double.parseDouble(alfas2.getText());
                    rateDepositoGoldExitHoleWumpus = Double.parseDouble(mediaDeposito2.getText());  
                    
                    stop = false;
                }
            }
         }
        );
        add(iniciar);


        padrao = new JButton("Unilizar dados do padrões");
        padrao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento){
                if(evento.getSource() == padrao){
                    n = 50;    
                    nPopulation = 20;
                    nGeration = 1000;
                    rateFeromonio = 1; //máximo 1
                    rateEvaporacao = 0.02;
                    alfa = 1.1;     
                    rateDepositoGoldExitHoleWumpus = 0.5;
                    
                    stop = false;
                }
            }
         }
        );
        add(padrao);
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getnPopulation() {
        return nPopulation;
    }

    public void setnPopulation(int nPopulation) {
        this.nPopulation = nPopulation;
    }

    public int getnGeration() {
        return nGeration;
    }

    public void setnGeration(int nGeration) {
        this.nGeration = nGeration;
    }

    public double getRateFeromonio() {
        return rateFeromonio;
    }

    public void setRateFeromonio(double rateFeromonio) {
        this.rateFeromonio = rateFeromonio;
    }

    public double getRateEvaporacao() {
        return rateEvaporacao;
    }

    public void setRateEvaporacao(double rateEvaporacao) {
        this.rateEvaporacao = rateEvaporacao;
    }

    public double getAlfa() {
        return alfa;
    }

    public void setAlfa(double alfa) {
        this.alfa = alfa;
    }        

    public double getRateDepositoGoldExitHoleWumpus() {
        return rateDepositoGoldExitHoleWumpus;
    }

    public void setRateDepositoGoldExitHoleWumpus(double rateDepositoGoldExitHoleWumpus) {
        this.rateDepositoGoldExitHoleWumpus = rateDepositoGoldExitHoleWumpus;
    }
    
    
}
