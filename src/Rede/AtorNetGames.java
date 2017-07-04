/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rede;

import Entidades.Jogada.JogadaTabuleiro;
import Entidades.Jogador.Jogador;
import Enumeradores.Raca;
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
        System.out.println(posicao);
        if(posicao == 1){
            atorJogador.getJogadorMapa().recebeVez();
            atorJogador.getJogadorMapa().setVezJogada(1);
            atorJogador.posicionaJogadores(1, atorJogador.getJogadorMapa());
            
            atorJogador.setJogadorInimigo(new Jogador(Jogador.pegaRacaPeloNome(proxy.obterNomeAdversario(2))));
            atorJogador.getJogadorMapa().setVezJogada(2);
            atorJogador.posicionaJogadores(2, atorJogador.getJogadorInimigo());
            
        }else if(posicao == 2){
            atorJogador.getJogadorMapa().setVezJogada(2);
            atorJogador.posicionaJogadores(2, atorJogador.getJogadorMapa());
            
            atorJogador.setJogadorInimigo(new Jogador(Jogador.pegaRacaPeloNome(proxy.obterNomeAdversario(1))));
            atorJogador.getJogadorMapa().setVezJogada(1);
            atorJogador.posicionaJogadores(1, atorJogador.getJogadorInimigo());
        }
    }

    @Override
    public void finalizarPartidaComErro(String message) {
        JOptionPane.showMessageDialog(null , "Partida finalizada com erro");
        atorJogador.limpar();
    }

    @Override
    public void receberMensagem(String msg) {
         JOptionPane.showMessageDialog(null , msg);
    }

    @Override
    public void receberJogada(Jogada jogada) {
        JogadaTabuleiro jogadaRecebida = (JogadaTabuleiro) jogada;
        System.out.println("Jogada recebida");
        atorJogador.recebeJogada(jogadaRecebida);
    }

    @Override
    public void tratarConexaoPerdida() {
        JOptionPane.showMessageDialog(null , "Conexão perdida");
        atorJogador.limpar();
    }

    @Override
    public void tratarPartidaNaoIniciada(String message) {
        JOptionPane.showMessageDialog(atorJogador,
				"A partida não pode ser iniciada devido ao seguinte erro: "
						+ message);
        atorJogador.limpar();
    }
    
    public void enviaJogada(Jogada jogada) {
        try{
           proxy.enviaJogada(jogada); 
        }catch(NaoJogandoException e){
            JOptionPane.showMessageDialog(null , "Você não esta jogando");
            atorJogador.limpar();
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
