package br.com.btg.cegardenal.jokenpo.dto;

import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class JogadaRequest {

    @Size(min = 1)
    @NotNull( message = "Nome é obrigatório")
    private String name;

    @Size(min = 1)
    @NotNull( message = "Jogada é obrigatória")
    private String jogada;

    public JogadaRequest(){
    }

    public JogadaRequest(String nome, String jogada) {
        this.name = nome;
        this.jogada = jogada;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJogada() {
        return jogada;
    }

    public void setJogada(String jogada) {
        this.jogada = jogada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JogadaRequest that = (JogadaRequest) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(jogada, that.jogada);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, jogada);
    }

    @Override
    public String toString() {
        return "MoveRequest{" +
                "nome='" + name + '\'' +
                ", jogada='" + jogada + '\'' +
                '}';
    }

}