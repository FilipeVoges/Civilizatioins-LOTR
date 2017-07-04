/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Gollum;

import Entidades.Mapa.Posicao;
import Entidades.Tropa.Heroi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author filipe
 */
public class Gollum {
    protected Anel anel;
    protected boolean visivel;
    protected ArrayList<Charada> charadas;
    protected String simbolo = "¤";
    protected Posicao posicao;
    protected int turnosInvisiveis;
    /*Faltam atributos
    velocidadeMovimento
    charadasPerguntadas
    respostas
    */
    public Gollum(Posicao posicao){
        
        this.anel = new Anel();
        this.visivel = true;
        charadas = new ArrayList<>();
        this.posicao = posicao;
        this.turnosInvisiveis = -1;
        criaCharadas();
    
    }
    
    public void agir(){
        if(this.turnosInvisiveis >= 0){
            turnosInvisiveis++;
        }else if(this.turnosInvisiveis >= 4){
            setVisivel(true);
        }
    }
    
    
    public void criaCharadas(){
        
        /**************************************************/
        charadas.add(
            new Charada(
                //pergunta
                "Tem raízes misteriosas,\n" +
                "É mais alta que as frondosa\n" +
                "Sobe, sobe e também desce,\n" +
                "Mas não cresce nem descresce.",
                //resposta certa
                "Montanha",
                //respostas erradas
                "Árvore",
                "Pássaro"
            )
        );
      
        /**************************************************/
        charadas.add(
            new Charada(
                "Trinta cavalos na colina encantada,\n" +
                "Primeiro cerceiam,\n" +
                "Depois pisoteiam,\n" +
                "Depois não fazem nada.",
                //resposta certa
                "Dentes",
                //respostas erradas
                "Manada",
                "Ansiedade"
            )
        );
        
        /**************************************************/
        charadas.add(
            new Charada(
                "Sem asas volita, \n" +
                "Sem voz ele ulula,\n" +
                "Sem dentes mordica,\n" +
                "Sem boca murmura.",
                //resposta certa
                "Vento",
                //respostas erradas
                "Galinha",
                "Fantasma"
            )
        );
        

        /**************************************************/
        charadas.add(
            new Charada(
                "Não se pode ver, não se pode sentir,\n" +
                "Não se pode cheirar, não se pode ouvir.\n" +
                "Está sob as colinas e além das estrelas,\n" +
                "Cavidades vazias - ele vai enchê-las.\n" +
                "De tudo vem antes e vem em seguida,\n" +
                "Do riso é a morte, é o fim da vida.",
                //resposta certa
                "Escuridão",
                //respostas erradas
                "Assombração",
                "Universo"
            )
        );

         /**************************************************/
        charadas.add(
            new Charada(
                "Caixinhas sem gonzos, tampas ou cadeado, \n" +
                "Lá dentro escondido um tesouro dourado.",
                //resposta certa
                "Ovos",
                //respostas erradas
                "Pérola",
                "Escrínio"
            )
        );

         /**************************************************/
        charadas.add(
            new Charada(
                "Como a morte não tenho calor, \n" +
                "Vivo, mas sem respirar;\n" +
                "Sem sede, sempre a beber\n" +
                "Eucouraçado, sem tilintar.",
                //resposta certa
                "Peixe",
                //respostas erradas
                "Sapo",
                "Lagartixa"
            )
        );
        
        
         /**************************************************/
        charadas.add(
            new Charada(
                "Esta é a coisa que tudo devora\n" +
                "Feras, aves, plantas, flora.\n" +
                "Aço e ferro são sua comida,\n" +
                "E a dura pedra por ele moída;\n" +
                "Aos reis abate, a cidade arruína,\n" +
                "E a alta montanha faz pequenina.",
                //resposta certa
                "Tempo",
                //respostas erradas
                "Peste",
                "Ferrugem"
            )
        );
        
    }
        
    public ArrayList<Charada> mostraCharada(){

        ArrayList<Charada> c = new ArrayList<>();
        Collections.shuffle(this.charadas);           
        c.add(charadas.get(0));
        c.add(charadas.get(1));
        c.add(charadas.get(2));
        return c;
    }
    
    public Anel perdeAnel() {
        this.anel = null;
        return new Anel();
    }
    
    public boolean temAnel(){
        if(anel!= null)return true;
        else return false;
    }
    
    public void comer(Heroi heroi){
        heroi.recebeDano(8000);
    }

    public void pegaAnel(Anel anel) {
        this.anel = anel;
    }
    
    public boolean isVisivel() {
        return visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
        if(visivel){
            simbolo = "¤";
            this.turnosInvisiveis = -1;
        }
        else{
            simbolo = "";
            this.turnosInvisiveis = 0;
        }
    }
    
    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    @Override
    public String toString(){
        return simbolo;
    }
    
}
