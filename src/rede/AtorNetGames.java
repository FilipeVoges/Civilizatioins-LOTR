/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rede;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;

/**
 *
 * @author Filipe
 */
public class AtorNetGames implements OuvidorProxy {
    
    private AtorJogador atorJogador;
    private Proxy proxy;
    private boolean minhaVez = false;
    
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
            atorJogador.getInterfaceGrafica().showDialog(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void desconectar() {
        try {
            proxy.desconectar();
        } catch (NaoConectadoException e) {
            atorJogador.getInterfaceGrafica().showDialog(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void iniciarPartidaRede() {
	try {
            proxy.iniciarPartida(2);
	} catch (NaoConectadoException e) {
            atorJogador.getInterfaceGrafica().showDialog(e.getMessage());
            e.printStackTrace();
	}
    }

    @Override
    public void iniciarNovaPartida(Integer posicao) {
        minhaVez = posicao == 1 ? true : false;
	atorJogador.iniciarPartidaResposta(minhaVez);
    }
    
    @Override
    public void finalizarPartidaComErro(String message) {
        atorJogador.getInterfaceGrafica().showDialog(message);
    }

    @Override
    public void receberMensagem(String msg) {
        // TODO Auto-generated method stub
    }

    @Override
    public void receberJogada(Jogada jogada) {
        // TODO Auto-generated method stub
    }

    @Override
    public void tratarConexaoPerdida() {
        atorJogador.getInterfaceGrafica().showDialog(
            "A conexão com o servidor foi perdida!");
    }

    @Override
    public void tratarPartidaNaoIniciada(String message) {
        atorJogador.getInterfaceGrafica().showDialog(
            "Não foi possível iniciar a conversa");
    }
    
    public String obterNomeAdversario() {
        String nome = "";
        if (minhaVez) {
            nome = proxy.obterNomeAdversario(2);
        } else {
            nome = proxy.obterNomeAdversario(1);
        }

        return nome;
    }

	public boolean ehMinhaVez() {
		return this.minhaVez;
	}
    
}
