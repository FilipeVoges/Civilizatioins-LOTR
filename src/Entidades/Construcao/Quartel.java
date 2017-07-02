/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Construcao;

import Entidades.Cidade.Cidade;
import Entidades.Mapa.Posicao;
import Entidades.Tropa.Espadachim;
import java.io.Serializable;

/**
 *
 * @author filipe
 */
public class Quartel extends Construcao implements Serializable{
    protected String simbolo = "♖";

    public Quartel(Posicao pos, Cidade c) {
        //simbolo, posicao, recursoRecrutamento, vida, cidade
        super("♖", pos, 50, 100, c);
    }
    //função não existe no diagrama de classes
    public Espadachim recrutar(Posicao posDisponivel){
        return new Espadachim(posDisponivel, super.getCidade());
    }
}
