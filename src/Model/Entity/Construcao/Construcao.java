/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entity.Construcao;

/**
 *
 * @author filipe
 */
public class Construcao {
    private final String simbolo;
    
    public Construcao(String simbolo){
        this.simbolo = simbolo;
    }
    
    @Override
    public String toString(){
        return simbolo;
    }
    
}
