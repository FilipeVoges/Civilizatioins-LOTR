/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rede;

import Entidades.Jogada.JogadaMapa;
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
    
    protected final AtorJogador atorJogador;
    protected final Proxy proxy;
    protected int qtdeJogadores;
    
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
        System.out.println(posicao);
        if(posicao == 1){
            atorJogador.souPrimeiroAJogar(proxy.obterNomeAdversario(2)); 
        }else if(posicao == 2){
            atorJogador.souSegundoAJogar(proxy.obterNomeAdversario(1));
        }
    }
    
    public void finalizarPartida()  {
 
        try {
            proxy.finalizarPartida();
            atorJogador.finalizaJogo();
            JOptionPane.showMessageDialog(null ,"Você foi desconectado da partida");
        } catch (NaoJogandoException ex) {
            JOptionPane.showMessageDialog(null , "Você não esta numa partida");
        } catch (NaoConectadoException ex) {
            JOptionPane.showMessageDialog(null , "Você não esta conectado");
        }
  
    }

    @Override
    public void finalizarPartidaComErro(String message) {
        //JOptionPane.showMessageDialog(null , "Partida finalizada com erro");
        atorJogador.finalizaJogo();
    }

    @Override
    public void receberMensagem(String msg) {
         JOptionPane.showMessageDialog(null , msg);
    }
    
    public void enviaJogada(JogadaMapa jogada) {
        try{
           Jogada jogadaEnvia = jogada;
           proxy.enviaJogada(jogadaEnvia); 
        }catch(NaoJogandoException e){
            JOptionPane.showMessageDialog(null , "Você não esta jogando");
            atorJogador.finalizaJogo();
        }
    }

    @Override
    public void receberJogada(Jogada jogada) {
        JogadaMapa jogadaRecebida = (JogadaMapa) jogada;
        System.out.println("Jogada recebida");
        atorJogador.recebeJogada(jogadaRecebida);
    }

    @Override
    public void tratarConexaoPerdida() {
        JOptionPane.showMessageDialog(null , "Conexão perdida");
        atorJogador.desconectar();
    }

    @Override
    public void tratarPartidaNaoIniciada(String message) {
        JOptionPane.showMessageDialog(atorJogador,
				"A partida não pode ser iniciada devido ao seguinte erro: "
						+ message);
        atorJogador.finalizaJogo();
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
