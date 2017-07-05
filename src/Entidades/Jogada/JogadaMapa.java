/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Jogada;

import Enumeradores.TipoJogada;

/**
 *
 * @author ASUS-DEV
 */
public class JogadaMapa implements br.ufsc.inf.leobr.cliente.Jogada {
    
    protected Object atual;
    protected Object alvo;
    protected TipoJogada tipoJogada;
    
    public JogadaMapa(){
        super();
    }
    
    public JogadaMapa(Object atual, Object alvo, TipoJogada tipo){
        super();
        this.atual = atual;
        this.alvo = alvo;
        this.tipoJogada = tipo;
    }

    public Object getAtual() {
        return atual;
    }

    public Object getAlvo() {
        return alvo;
    }

    public TipoJogada getTipoJogada() {
        return tipoJogada;
    }

    public void setAtual(Object atual) {
        this.atual = atual;
    }

    public void setAlvo(Object alvo) {
        this.alvo = alvo;
    }

    public void setTipoJogada(TipoJogada tipoJogada) {
        this.tipoJogada = tipoJogada;
    }
    
    
    
}
