/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Construcao;

import Entidades.Cidade.Cidade;
import Entidades.Mapa.Posicao;
import Entidades.Tropa.Heroi;

/**
 *
 * @author filipe
 */
public class Principal extends Construcao{
    private String Simbolo = "⌂";
    private boolean heroiConjurado;

    public Principal(Posicao pos, Cidade c) {
        super("⌂", pos, 1, 50, c);
        //simbolo, posicao, tempoRecrutamento, recursoRecrutamento
        this.heroiConjurado= false;
    }
    
    public Heroi recrutar(Posicao posDisponivel){
        return new Heroi(posDisponivel, super.getCidade());
    }

    public boolean isHeroiConjurado() {
        return heroiConjurado;
    }

    public void setHeroiConjurado(boolean heroiConjurado) {
        this.heroiConjurado = heroiConjurado;
    }
    
    
}
