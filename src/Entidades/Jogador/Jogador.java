/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Jogador;

import Entidades.Cidade.Cidade;
import Enumeradores.Raca;
import Enumeradores.TipoJogada;

/**
 *
 * @author filipe
 */
public class Jogador {
    protected String nome;
    protected Cidade cidade;
    protected boolean vencedor;
    protected boolean temVez;
    protected TipoJogada tipoClique;
    protected int vezJogada;
    protected Raca raca;
    protected String cor; // existe apenas no diagrama de classe.
    
    public Jogador(String nome, Raca raca, int vezJogada){
        this.nome = nome;
        this.raca = raca;
        this.vezJogada = vezJogada;
        this.tipoClique = TipoJogada.SELECAO;
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

    public void recebeVez() { //não tem no diagrama de classes
        this.temVez = true;
        this.cidade.recebeRecursos();
    }
    
    public void passaVez() { //não tem no diagrama de classes
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

    public String getNome() {
        return nome;
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

}
