/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Tropa;

import Entidades.Cidade.Cidade;
import Entidades.Mapa.Posicao;

/**
 *
 * @author filipe
 */
public class Tropa {
    
    protected int vida;
    protected int velocidadeMovimento;
    protected int forca;
    protected int distanciaAtaque;
    protected int distanciaMovimento;
    protected Posicao posicaoAtual;
    protected Posicao posicaoDestino;
    protected int resistencia;
    protected String simbolo;
    protected Cidade cidade;
    //Diagrama de classes possui o atributo OCUPADO :boolean
    /*
    Faltam as seguintes funções:
    movimentaTropa(), verificaTropaMovimentada(): boolean, mudarOcupado(),
    movimentar(distanciaMovimentada):Posicao
    calculaDistanciaMovimentada(distancia):int
    isDestruido(): boolean,
    atacar(alvo, destino), atacar(alvo:Construcao), atacar(alvo:Tropa)
    tropaSelecionada():Tropa
    verificaTipoTropa():Tropa
    perdeAnel()
    recrutarTropaBonus()
    */
    public Tropa(
            int vida, 
            int velocidadeMovimento, 
            int forca, 
            int distanciaAtaque, 
            int resistencia, 
            String simbolo, 
            Posicao posicao,
            Cidade cidade
    ) {
        this.vida = vida;
        this.velocidadeMovimento = velocidadeMovimento;
        this.forca = forca;
        this.distanciaAtaque = distanciaAtaque;
        this.distanciaMovimento = 2;
        this.resistencia = resistencia;
        this.simbolo = simbolo;
        this.posicaoAtual = posicao;
        this.cidade = cidade;
    }
    //diagrama: calculaDistancia(tropaAliada, destino): int
    public int calculaDistancia(Posicao destino){
        
        int posY = Math.abs(posicaoAtual.getY() - destino.getY());
        int posX = Math.abs(posicaoAtual.getX() - destino.getX());
        
        return posY + posX; 
    }
    
    
    public int calculaDano(Tropa alvo){
        double vantagem = this.calculaVantagem(alvo);
        int dano = (int) ((this.getForca()*vantagem) - alvo.getResistencia());
        return dano;
    }
    
    public double calculaVantagem(Tropa alvo){
        double vantagem = 1;
        if(this instanceof Espadachim && alvo instanceof Cavaleiro){
            vantagem = 1.8;
        } else if(this instanceof Arqueiro && alvo instanceof Espadachim){
            vantagem = 1.8;
        } else if(this instanceof Cavaleiro && alvo instanceof Arqueiro){
            vantagem = 1.8;
        } else if(this instanceof Espadachim && alvo instanceof Arqueiro){
            vantagem = 0.3;
        } else if(this instanceof Arqueiro && alvo instanceof Cavaleiro){
            vantagem = 0.3;
        } else if(this instanceof Cavaleiro && alvo instanceof Espadachim){
            vantagem = 0.3;
        }
        
        return vantagem;
    }
    
    public int calculaRetalicao(Tropa atacante, Tropa alvo){
        double vantagem = alvo.calculaVantagem(atacante);
        int retaliacao = (int) ((alvo.getForca()/2)*vantagem);
        return retaliacao;
    }
    
    @Override
    public String toString(){
        return simbolo;
    }
    
    public int getVida() {
        return vida;
    }

    public int getVelocidadeMovimento() {
        return velocidadeMovimento;
    }

    public int getForca() {
        return forca;
    }

    public int getDistanciaAtaque() {
        return distanciaAtaque;
    }
    
    public int getResistencia() {
        return resistencia;
    }

    public Posicao getPosicao() {
        return posicaoAtual;
    }
    
    public void setPosicaoAtual(Posicao posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }

    public Posicao getPosicaoDestino() {
        return posicaoDestino;
    }

    public void setPosicaoDestino(Posicao posicaoDestino) {
        this.posicaoDestino = posicaoDestino;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public int getDistanciaMovimento() {
        return distanciaMovimento;
    }
  
}
