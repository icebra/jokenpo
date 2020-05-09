package br.com.btg.cegardenal.jokenpo.entity;

import java.util.Objects;

import javax.persistence.Entity;

@Entity
public class JogadorEntity {
    private String name;

    public JogadorEntity(){
    }

    public JogadorEntity(String name) {
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
        JogadorEntity that = (JogadorEntity) o;
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