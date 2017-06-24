/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Construcao;

import Entidades.Cidade.Cidade;
import Entidades.Mapa.Posicao;
import Entidades.Tropa.Espadachim;

/**
 *
 * @author filipe
 */
public class Quartel extends Construcao{
    private String simbolo = "♖";

    public Quartel(Posicao pos, Cidade c) {
        //simbolo, posicao, tempoRecrutamento, recursoRecrutamento
        super("♖", pos, 3, 100, c);
    }
    
    public Espadachim recrutar(Posicao posDisponivel){
        return new Espadachim(posDisponivel, super.getCidade());
    }
}
