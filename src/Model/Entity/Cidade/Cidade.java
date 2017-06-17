/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entity.Cidade;

import Model.Entity.Construcao.Construcao;
import Model.Entity.Tropa.Tropa;
import java.util.ArrayList;

/**
 *
 * @author filipe
 */
public class Cidade {
    
    private int recursos;
    private String nome;
    private ArrayList<Construcao> construcoes;
    private ArrayList<Tropa> tropas;
    
    public Cidade(String nome, int recursosIniciais){
        this.nome = nome;
        this.recursos = recursosIniciais;
    }

    public int getRecursos() {
        return recursos;
    }

    public void setRecursos(int recursos) {
        this.recursos = recursos;
    }

    public String getNome() {
        return nome;
    }
    
    
    
    
}
