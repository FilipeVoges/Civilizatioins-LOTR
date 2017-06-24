/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Construcao;

import Entidades.Cidade.Cidade;
import Entidades.Mapa.Posicao;
import Entidades.Tropa.Cavaleiro;

/**
 *
 * @author filipe
 */
public class Estabulo extends Construcao{
    private String simbolo = "♞";
    
    public Estabulo(Posicao pos, Cidade c){
        //simbolo, posicao, tempoRecrutamento, recursoRecrutamento
        super("♞", pos, 2, 70, c);
    }
    
    public Cavaleiro recrutar(Posicao posDisponivel){
        return new Cavaleiro(posDisponivel, super.getCidade());
    }
    
    
}
