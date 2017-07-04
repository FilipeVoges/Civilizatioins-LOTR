/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Tropa;

import Entidades.Cidade.Cidade;
import Entidades.Construcao.Construcao;
import Entidades.Mapa.Posicao;

/**
 *
 * @author filipe
 */
public class Tropa {
    
    protected int vida;
    //protected int velocidadeMovimento;
    protected int forca;
    protected int distanciaAtaque;
    protected int distanciaMovimento;
    protected Posicao posicaoAtual;
    //protected Posicao posicaoDestino;
    protected int resistencia;
    protected String simbolo;
    protected Cidade cidade;
    protected boolean vivo;
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
            int distanciaMovimento, 
            int forca, 
            int distanciaAtaque, 
            int resistencia, 
            String simbolo, 
            Posicao posicao,
            Cidade cidade
    ) {
        this.vida = vida;
        this.forca = forca;
        this.distanciaAtaque = distanciaAtaque;
        this.distanciaMovimento = distanciaMovimento;
        this.resistencia = resistencia;
        this.simbolo = simbolo;
        this.posicaoAtual = posicao;
        this.cidade = cidade;
        this.vivo = true;
    }
    //diagrama: calculaDistancia(tropaAliada, destino): int
    public int calculaDistancia(Posicao destino){
        
        int posY = Math.abs(posicaoAtual.getY() - destino.getY());
        int posX = Math.abs(posicaoAtual.getX() - destino.getX());
        
        return posY + posX; 
    }
    
    
    public int calculaDano(Tropa alvo){
        double vantagem = this.calculaVantagem(alvo);
        int dano = (int) ((this.getForca() * vantagem) - alvo.getResistencia());
        return dano;
    }
    
    public int calculaDano(Construcao alvo){
        return (int) (this.getForca());
    }
    
    public int recebeDano(int dano) {
        vida -= dano;
        if(vida <= 0) {
            vida = 0;
            simbolo = "✞";
            vivo = false;
        }
        return vida;
    }
    
    private double calculaVantagem(Tropa alvo){
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
        } else if(this instanceof Heroi){
            vantagem = 1.9;
        } else if(this instanceof Mago){
            vantagem = 1.2;
        }
        
        return vantagem;
    }
    
    public int calculaRetalicao( Tropa alvo){
        double vantagem = alvo.calculaVantagem(this);
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
    
    public boolean isVivo() {
        return vivo;
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
