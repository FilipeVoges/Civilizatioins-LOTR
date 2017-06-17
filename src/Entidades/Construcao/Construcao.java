/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Construcao;

import Entidades.Mapa.Posicao;

/**
 *
 * @author filipe
 */
public class Construcao {
    
    private int tempoRecrutamento;
    private int nivel; 
    private boolean destruido;
    private int recursoRecrutamento;
    private Posicao posicao;
    private int vida;
    private final String simbolo;
    private int progressoReforma;
    private int radius;
    
    
    public Construcao(String simbolo, Posicao pos, int tempoRecrutamento, int recursoRecrutamento){
        this.simbolo = simbolo;
        this.posicao = pos;
        this.tempoRecrutamento = tempoRecrutamento;
        this.recursoRecrutamento = recursoRecrutamento;
        this.vida = 50;
        this.nivel = 1;
        this.destruido = false;
    }
    
    public Posicao getPosicao() {
        return posicao;
    }
    
    public int getVida() {
        return vida;
    }
    
    @Override
    public String toString(){
        return simbolo;
    }

    public int getTempoRecrutamento() {
        return tempoRecrutamento;
    }

    public boolean isDestruido() {
        return destruido;
    }

    public void setDestruido(boolean destruido) {
        this.destruido = destruido;
    }

    public int getRecursoRecrutamento() {
        return recursoRecrutamento;
    }
    
    public int getProgressoReforma() {
        return progressoReforma;
    }

    public void setProgressoReforma(int progressoReforma) {
        this.progressoReforma = progressoReforma;
    }
    
    
}
