package br.com.btg.cegardenal.jokenpo.dto;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import br.com.btg.cegardenal.jokenpo.enumeration.EnumMovement;

public class JogadaResponse {

	@NotNull(message = "Jogador é obrigatório")
	private JogadorResponse jogador;

	@NotNull(message = "Jogada é obrigatpótria")
	private EnumMovement jogada;

	public JogadaResponse() {
	}

	public JogadaResponse(JogadorResponse jogador, EnumMovement jogada) {
		this.jogador = jogador;
		this.jogada = jogada;
	}

	public JogadorResponse getJogador() {
		return jogador;
	}

	public void setPlayer(JogadorResponse jogador) {
		this.jogador = jogador;
	}

	public EnumMovement getJogada() {
		return jogada;
	}

	public void setJogada(EnumMovement jogada) {
		this.jogada = jogada;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		JogadaResponse that = (JogadaResponse) o;
		return Objects.equals(jogador, that.jogador) && jogada == that.jogada;
	}

	@Override
	public int hashCode() {
		return Objects.hash(jogador, jogada);
	}

	@Override
	public String toString() {
		return "JogadaResponse{" + "jogador=" + jogador + ", jogada=" + jogada + '}';
	}

}