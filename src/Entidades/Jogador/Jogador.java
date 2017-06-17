/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Jogador;

import java.awt.Color;

/**
 *
 * @author filipe
 */
public class Jogador {
    private String nome;
    private Color cor;
    private boolean vencedor;
    private boolean temVez;
    private int tipoClique;
    private int vezJogada;
    
    public Jogador(String nome, Color cor, int vezJogada){
        this.nome = nome;
        this.cor = cor;
        this.vezJogada = vezJogada;
    }

    public boolean isVencedor() {
        return vencedor;
    }

    public void setVencedor(boolean vencedor) {
        this.vencedor = vencedor;
    }

    public boolean verificaVez() {
        return temVez;
    }

    public void recebeVez() {
        this.temVez = true;
    }
    
    public void passaVez() {
        this.temVez = false;
    }

    public int getTipoClique() {
        return tipoClique;
    }

    public void setTipoClique(int finalidadeClique) {
        this.tipoClique = tipoClique;
    }

    public int getVezJogada() {
        return vezJogada;
    }

    public String getNome() {
        return nome;
    }

    public Color getCor() {
        return cor;
    }
    
    
    
    
    
    
    
    
    
}
