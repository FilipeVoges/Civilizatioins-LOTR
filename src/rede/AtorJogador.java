/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rede;

/**
 *
 * @author Filipe
 */
public class AtorJogador {
   private Jogo jogo;
	private String nome;

	private AtorNetGames atorNetGames;
	private InterfaceGrafica interfaceGrafica;

	public AtorJogador() {
		this.interfaceGrafica = new InterfaceGrafica(this);
		this.interfaceGrafica.desabilitarBotoesJogada();
		this.showNameQuestion();
		atorNetGames = new AtorNetGames(this);
	}

	private void showNameQuestion() {
		this.nome = this.interfaceGrafica.showNameQuestion();
		if (this.nome.isEmpty()) {
			this.nome = "Anônimo";
		}
	}

	public InterfaceGrafica getInterfaceGrafica() {
		return interfaceGrafica;
	}

	public void conectar() {
		atorNetGames.conectar(nome, "localhost");
	}

	public void iniciarPartidaPedido() {
		atorNetGames.iniciarPartidaRede();
	}

	public void iniciarPartidaResposta(boolean comecoJogando) {
		interfaceGrafica.desabilitarIniciarPartida();
		String nomeOutroJogador = atorNetGames.obterNomeAdversario();
		jogo = new Jogo(this);
	
		if (comecoJogando) {
			jogo.criarJogador(this.nome, true);
			jogo.criarJogador(nomeOutroJogador, false);
			this.interfaceGrafica.showDialog(
					"Jogo iniciado!\nVocê começa jogando.");
			this.interfaceGrafica.atualizarNomeJogador1(this.nome, true);
			this.interfaceGrafica.atualizarNomeJogador2(nomeOutroJogador,
					false);
			interfaceGrafica.habilitarBotoesJogada();
		} else {
			jogo.criarJogador(nomeOutroJogador, false);
			jogo.criarJogador(this.nome, true);
			interfaceGrafica
					.showDialog("Jogo iniciado!\nAguarde a jogada de seu adversário.");
			this.interfaceGrafica.atualizarNomeJogador1(nomeOutroJogador,
					false);
			this.interfaceGrafica.atualizarNomeJogador2(this.nome, true);
		}
		this.interfaceGrafica.atualizarInterface(jogo.getEstado());
	}

	public void receberEstado(Estado estado) {
		interfaceGrafica.atualizarInterface(estado);
		if (!jogo.setEstado(estado)) {
			interfaceGrafica.desabilitarBotoesJogada();
			interfaceGrafica.habilitarIniciarPartida();
		} else {
			interfaceGrafica.habilitarBotoesJogada();
		}
	}

	public void enviarEstado() {
		interfaceGrafica.desabilitarBotoesJogada();
		atorNetGames.enviarEstado(jogo.getEstado());
	}

	public void atacar() {
		if (atorNetGames.ehMinhaVez()) {
			if (jogo.atacar()) {
				this.enviarEstado();
				interfaceGrafica.atualizarInterface(jogo.getEstado());
			} else {
				interfaceGrafica.showDialog("Não é possivel atacar.");
			}
		} else {
			interfaceGrafica.showDialog("Não é sua vez.");
		}
	}

	public void construirTropas() {
		if (atorNetGames.ehMinhaVez()) {
			if (jogo.construirTropas()) {
				this.enviarEstado();
				interfaceGrafica.atualizarInterface(jogo.getEstado());
			} else {
				interfaceGrafica.showDialog("Não é possivel construir tropas.");
			}
		} else {
			interfaceGrafica.showDialog("Não é sua vez.");
		}
	}

	public void fortalecerMuralha() {
		if (atorNetGames.ehMinhaVez()) {
			if (jogo.fortalecerMuralha()) {
				this.enviarEstado();
				interfaceGrafica.atualizarInterface(jogo.getEstado());
			} else {
				interfaceGrafica
						.showDialog("Não é possivel fortalecer a muralha.");
			}
		} else {
			interfaceGrafica.showDialog("Não é sua vez.");
		}
	}

	public void coletarRecursos() {
		if (atorNetGames.ehMinhaVez()) {
			jogo.coletarRecursos();
			this.enviarEstado();
			interfaceGrafica.atualizarInterface(jogo.getEstado());
		} else {
			interfaceGrafica.showDialog("Não é sua vez.");
		}
	}

	public void renderSe() {
		if (atorNetGames.ehMinhaVez()) {
			jogo.renderSe();
			this.enviarEstado();
			interfaceGrafica.atualizarInterface(jogo.getEstado());
			this.interfaceGrafica.showDialog("Você se rendeu.\nO jogo acabou.");
			interfaceGrafica.habilitarIniciarPartida();
		} else {
			interfaceGrafica.showDialog("Não é sua vez.");
		}
	}

	public void avisarVencedor() {
		interfaceGrafica.desabilitarBotoesJogada();
		interfaceGrafica.habilitarIniciarPartida();
		this.interfaceGrafica.showDialog("Parabéns! Você é o vencedor! ;)\nO jogo acabou.");
	}
	
	public void avisarRendeuSe() {
		interfaceGrafica.desabilitarBotoesJogada();
		this.interfaceGrafica.showDialog(
				"O outro jogador se rendeu.\nParabéns! Você é o vencedor! ;)\nO jogo acabou.");
	}
	
	public void avisarPerdedor() {
		interfaceGrafica.desabilitarBotoesJogada();
		this.interfaceGrafica.showDialog(
				"Você perdeu :(\nO jogo acabou.");
	}

	public void desconectar() {
		atorNetGames.desconectar();
	}
}