package br.com.btg.cegardenal.jokenpo.entity;

import java.util.Objects;

import javax.persistence.Entity;

import br.com.btg.cegardenal.jokenpo.enumeration.EnumMovement;

@Entity
public class JogadaEntity {
    private JogadorEntity jogador;
    private EnumMovement enumMovement;

    public JogadaEntity(){
    }

    public JogadaEntity(JogadorEntity jogador, EnumMovement enumMovement) {
        this.jogador = jogador;
        this.enumMovement = enumMovement;
    }

    public JogadorEntity getJogador() {
        return jogador;
    }

    public void setJogador(JogadorEntity jogador) {
        this.jogador = jogador;
    }

    public EnumMovement getEnumMovement() {
        return enumMovement;
    }

    public void setEnumMovement(EnumMovement enumMovement) {
        this.enumMovement = enumMovement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        JogadaEntity that = (JogadaEntity) o;
        return Objects.equals(jogador, that.jogador) &&
                Objects.equals(enumMovement, that.enumMovement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), jogador, enumMovement);
    }

    @Override
    public String toString() {
        return "JogadaEntity{" +
                "jogador=" + jogador +
                ", enumMovement=" + enumMovement +
                '}';
    }

}