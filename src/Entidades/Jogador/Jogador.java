/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Jogador;

import Entidades.Cidade.Cidade;
import Enumeradores.Raca;
import Enumeradores.TipoJogada;
import java.io.Serializable;

/**
 *
 * @author filipe
 */
public class Jogador implements Serializable{
    protected Cidade cidade;
    protected boolean vencedor;
    protected boolean temVez;
    protected TipoJogada tipoClique;
    protected int vezJogada;
    protected Raca raca;
    
    public Jogador(Raca raca){
        this.raca = raca;
        this.temVez = false;
        this.tipoClique = TipoJogada.SELECAO;
        this.cidade = new Cidade(this);
        this.vezJogada = 0;
    }

    public boolean isVencedor() {
        return vencedor;
    }

    public void setVencedor(boolean vencedor) {
        this.vencedor = vencedor;
    }

    public void setVezJogada(int vezJogada) {
        this.vezJogada = vezJogada;
    }
    

    public boolean verificaVez() {
        return temVez;
    }

    public void recebeVez() { 
        this.temVez = true;
        this.cidade.recebeRecursos();
    }
    
    public void passaVez() { 
        this.temVez = false;
    }

    public TipoJogada getTipoClique() {
        return tipoClique;
    }

    public void setTipoClique(TipoJogada tipoClique) {
        this.tipoClique = tipoClique;
    }

    public int getVezJogada() {
        return vezJogada;
    }


    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Raca getRaca() {
        return raca;
    }
    
    
    public static Raca pegaRacaPeloNome(String nome){
        switch(nome){
            case "Elfo":
                return Raca.ELFO;
            case "Humano":
                return Raca.HUMANO;
            case "Uruk Hai":
                return Raca.URUK_HAI;
            case "Orc":
                return Raca.ORC; 
        }
        return null;
    }

}
