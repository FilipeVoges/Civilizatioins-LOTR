/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Construcao;

import Entidades.Cidade.Cidade;
import Entidades.Mapa.Posicao;
import Entidades.Tropa.Heroi;
import java.io.Serializable;

/**
 *
 * @author filipe
 */
public class Principal extends Construcao implements Serializable{
    protected String Simbolo = "⌂";
    protected boolean heroiConjurado;

    public Principal(Posicao pos, Cidade c) {
        //simbolo, posicao, tempoRecrutamento, recursoRecrutamento
        super("⌂", pos, 100, 300, c);
        this.heroiConjurado= false;
    }
    //função não existe no diagrama de classes
    public Heroi recrutar(Posicao posDisponivel){
        
        Heroi heroi = null;
        switch(super.cidade.getJogador().getRaca()){
            
            case HUMANO:
                // nome, velocidadeMovimento, forca, distanciaAtaque, resistencia, posicao, cidade
                heroi = new Heroi("Aragorn", 2, 25, 3, 4, posDisponivel, cidade);
            break;
            
            case ELFO:
                // nome, velocidadeMovimento, forca, distanciaAtaque, resistencia, posicao, cidade
                heroi = new Heroi("Legolas", 1, 25, 5, 4, posDisponivel, cidade);
            break;
            
            case URUK_HAI:
                // nome, velocidadeMovimento, forca, distanciaAtaque, resistencia, posicao, cidade
                heroi = new Heroi("Lurtz", 1, 25, 1, 4, posDisponivel, cidade);
            break;
            
            case ORC:
                // nome, velocidadeMovimento, forca, distanciaAtaque, resistencia, posicao, cidade
                heroi = new Heroi("Azog", 3, 25, 1, 4, posDisponivel, cidade);
            break;
            
            default:
            break;
            
        }
        return heroi;
    }

    public boolean isHeroiConjurado() {
        return heroiConjurado;
    }

    public void setHeroiConjurado(boolean heroiConjurado) {
        this.heroiConjurado = heroiConjurado;
    }
    
    
}
