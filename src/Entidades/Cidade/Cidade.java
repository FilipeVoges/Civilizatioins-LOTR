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
import java.util.ArrayList;


public class Cidade {
    
    protected int recursos;
    protected String nome;
    protected ArrayList<Construcao> construcoes;
    protected Jogador jogador;
  
    
    public Cidade(Jogador dono){
        
        switch(dono.getRaca()){
        
            case HUMANO:
                this.nome = "Gondor";
            break;
            
            case ELFO:
                this.nome = "Valfenda";
            break;
            
            case URUK_HAI:
                this.nome = "Isengard";
            break;
            
            case ORC:
                this.nome = "Portão Negro";
            break;
            
            default:
            break;
        }
        
        this.recursos = 300;
        dono.setCidade(this);
        this.jogador = dono;
        construcoes = new ArrayList<>();
    }
    
     public void recebeRecursos(){
         
        int qtdeConstrucao = 0;
        for(int i = 0; i < construcoes.size(); i++){
            if(construcoes.get(i).isDestruido() == false){
                qtdeConstrucao++;
            }
        }
        recursos += 50 * qtdeConstrucao;
    }

    public int getRecursos() {
        return recursos;
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
