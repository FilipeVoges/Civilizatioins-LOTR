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
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;

/**
 *
 * @author Filipe
 */
public class AtorNetGames implements OuvidorProxy{
    
    private AtorJogador atorJogador;
    private Proxy proxy;
    
    public AtorNetGames(AtorJogador atorJogador) {
        super();
        this.atorJogador = atorJogador;
        proxy = Proxy.getInstance();
        proxy.addOuvinte(this);
    }
    
    public void conectar(String nome, String servidor) {
        try {
            proxy.conectar(servidor, nome);
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
    
    public void desconectar() {
        try {
            proxy.desconectar();
        } catch (NaoConectadoException e) {
            //Faz teu JOptionPane feio aqui Daniel
            e.printStackTrace();
        }
    }
    
    public void iniciarPartidaRede() {
        try {
            proxy.iniciarPartida(2);
        } catch (NaoConectadoException e) {
            //Faz teu JOptionPane feio aqui Daniel
            e.printStackTrace();
	}
    }
    
    @Override
    public void iniciarNovaPartida(Integer posicao) {
        //seta o jogador que vai começar
    }

    @Override
    public void finalizarPartidaComErro(String message) {
        //Faz teu JOptionPane feio aqui Daniel
    }

    @Override
    public void receberMensagem(String msg) {
        // TODO Auto-generated method stub
    }

    @Override
    public void receberJogada(Jogada jogada) {
//        Codigo do Tribal Wars, ver como vamos fazer
//        Estado estado = (Estado) jogada;
//	atorJogador.receberEstado(estado);
//	minhaVez = true;
    }

    @Override
    public void tratarConexaoPerdida() {
        //Faz teu JOptionPane feio aqui Daniel "A Casa Caiu Vacilão"
    }

    @Override
    public void tratarPartidaNaoIniciada(String message) {
        //Faz teu JOptionPane feio aqui Daniel "Já falei que a Casa Caiu Vacilão"
    }
    
    public String obterNomeAdversario() {
        String nome = "";
        if (/*MinhaVez*/true) {
            nome = proxy.obterNomeAdversario(2);
        } else {
            nome = proxy.obterNomeAdversario(1);
        }

        return nome;
    }
    
}
