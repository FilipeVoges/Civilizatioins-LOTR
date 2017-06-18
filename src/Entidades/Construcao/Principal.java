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
public class Principal extends Construcao{
    private String Simbolo = "⌂";
    private boolean heroiConjurado;

    public Principal(Posicao pos) {
        super("⌂", pos, 1, 50);
        this.heroiConjurado= false;
    }

    public boolean isHeroiConjurado() {
        return heroiConjurado;
    }

    public void setHeroiConjurado(boolean heroiConjurado) {
        this.heroiConjurado = heroiConjurado;
    }
    
    
}
