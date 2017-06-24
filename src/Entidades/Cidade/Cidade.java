/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Cidade;

import Entidades.Construcao.Arquearia;
import Entidades.Construcao.Construcao;
import Entidades.Construcao.Estabulo;
import Entidades.Construcao.Principal;
import Entidades.Construcao.Quartel;
import Entidades.Jogador.Jogador;
import Entidades.Mapa.Posicao;
import Entidades.Tropa.Tropa;
import java.util.ArrayList;

/**
 *
 * @author filipe
 */
public class Cidade {
    
    private int recursos;
    private String nome;
    private ArrayList<Construcao> construcoes;
    private Jogador jogador;
    
    public Cidade(String nome, int recursosIniciais, Jogador dono){
        this.nome = nome;
        this.recursos = recursosIniciais;
        dono.setCidade(this);
        this.jogador = dono;
        construcoes = new ArrayList<>();
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

    public Jogador getJogador() {
        return jogador;
    }

    public boolean verificaUltimaConstrucao(){
        boolean todosDestruidos = true;
        
        for(int i = 0; i < construcoes.size(); i++){
            if(construcoes.get(i).isDestruido() == false){
                todosDestruidos = false;
                break;
            }
        }
        
        return todosDestruidos;
    }
    
    public Construcao construir(String tipo, Posicao pos){
        Construcao cons = null;
        
        if(tipo.equals(Arquearia.class.getSimpleName())){
            cons = new Arquearia(pos, this);
        }else if(tipo.equals(Estabulo.class.getSimpleName())){
            cons = new Estabulo(pos, this);
        }else if(tipo.equals(Quartel.class.getSimpleName())){
            cons = new Quartel(pos, this);
        }else if(tipo.equals(Principal.class.getSimpleName())){
            cons = new Principal(pos, this);
        }
        
        if(cons != null) construcoes.add(cons);
        
        return cons;
    }
    
    
    
    
}
