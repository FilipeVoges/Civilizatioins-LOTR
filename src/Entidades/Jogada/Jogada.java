/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Jogada;

/**
 *
 * @author ASUS-DEV
 */
public class Jogada {
    
    protected Object antigo;
    protected Object modificado;
    
    public Jogada(Object velho, Object novo){
        this.antigo = velho;
        this.modificado = novo;
    }

    public Object getAntigo() {
        return antigo;
    }

    public Object getModificado() {
        return modificado;
    }
    
    
    
}
