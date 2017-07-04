/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rede;

import InterfaceGrafica.AtorJogador;
import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import javax.swing.JOptionPane;

/**
 * @author Filipe
 */
public class AtorNetGames implements OuvidorProxy{
    
    private AtorJogador atorJogador;
    private Proxy proxy;
    int qtdeJogadores;
    
    public AtorNetGames(AtorJogador atorJogador) {
        super();
        this.atorJogador = atorJogador;
        proxy = Proxy.getInstance();
        proxy.addOuvinte(this);
        qtdeJogadores = 0;
    }
    
    public void conectar(String nome, String servidor) throws JahConectadoException, NaoPossivelConectarException, ArquivoMultiplayerException {
        
        proxy.conectar(servidor, nome);
       
    }
    
    public void desconectar() throws NaoConectadoException {
        proxy.desconectar();
      
    }
    
    public void iniciarPartidaRede(int qtdeJogadores) throws NaoConectadoException, Exception {
        this.qtdeJogadores = qtdeJogadores;
        if(qtdeJogadores > 4)throw new Exception("Limite Jogadores excedido");
        proxy.iniciarPartida(qtdeJogadores);
   
    }
    
    @Override
    public void iniciarNovaPartida(Integer posicao) {
        proxy.iniciarNovaPartida(posicao);
    }

    @Override
    public void finalizarPartidaComErro(String message) {
        JOptionPane.showMessageDialog(null , "Partida finalizada com erro");
    }

    @Override
    public void receberMensagem(String msg) {
         JOptionPane.showMessageDialog(null , msg);
    }

    @Override
    public void receberJogada(Jogada jogada) {
        Entidades.Jogada.JogadaTabuleiro jogadaRecebida = (Entidades.Jogada.JogadaTabuleiro) jogada;
        atorJogador.recebeJogada(jogadaRecebida);
    }

    @Override
    public void tratarConexaoPerdida() {
        JOptionPane.showMessageDialog(null , "Conexão perdida");
    }

    @Override
    public void tratarPartidaNaoIniciada(String message) {
        JOptionPane.showMessageDialog(null , "Partida não iniciada, tente novamente");
    }
    
    public void enviaJogada(Entidades.Jogada.JogadaTabuleiro jogada) {
        try{
            
            Jogada jogadaEnviar = jogada;
            proxy.enviaJogada(jogada);
            
        }catch(NaoJogandoException e){
            JOptionPane.showMessageDialog(null , "Você não esta jogando");
        }
    }
    
    /*public String obterNomeAdversario() {
        String nome = "";
        if (true) {
            nome = proxy.obterNomeAdversario(2);
        } else {
            nome = proxy.obterNomeAdversario(1);
        }

        return nome;
    }*/
    
}
