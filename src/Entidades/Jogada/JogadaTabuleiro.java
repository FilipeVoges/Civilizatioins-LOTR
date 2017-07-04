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
public class JogadaTabuleiro implements br.ufsc.inf.leobr.cliente.Jogada {
    
    protected Object antigo;
    protected Object modificado;
    protected TipoJogada tipoJogada;
    
    public JogadaTabuleiro(){}
    
    public JogadaTabuleiro(Object velho, Object novo, TipoJogada tipo){
        this.antigo = velho;
        this.modificado = novo;
        this.tipoJogada = tipo;
    }

    public Object getAntigo() {
        return antigo;
    }

    public Object getModificado() {
        return modificado;
    }

    public TipoJogada getTipoJogada() {
        return tipoJogada;
    }

    public void setAntigo(Object antigo) {
        this.antigo = antigo;
    }

    public void setModificado(Object modificado) {
        this.modificado = modificado;
    }

    public void setTipoJogada(TipoJogada tipoJogada) {
        this.tipoJogada = tipoJogada;
    }
    
    
    
}
