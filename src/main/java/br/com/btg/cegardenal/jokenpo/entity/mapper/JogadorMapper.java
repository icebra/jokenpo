package br.com.btg.cegardenal.jokenpo.entity.mapper;

import java.util.Objects;

import javax.persistence.Entity;

@Entity
public class JogadorMapper {
    private String name;

    public JogadorMapper(){
    }

    public JogadorMapper(String name) {
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
        if (!super.equals(o)) return false;
        JogadorMapper that = (JogadorMapper) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return "JogadorEntity{" +
                "nome='" + name + '\'' +
                '}';
    }

}