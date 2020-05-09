package br.com.btg.cegardenal.jokenpo.dto;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JogadorResponse {

    @NotNull
    @JsonProperty(value = "name")
    private String name;

    public JogadorResponse(){
    }

    public JogadorResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JogadorResponse that = (JogadorResponse) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "JogadorResponse{" +
                "name='" + name + '\'' +
                '}';
    }

}