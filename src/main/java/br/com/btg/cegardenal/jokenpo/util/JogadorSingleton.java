package br.com.btg.cegardenal.jokenpo.util;

import java.util.ArrayList;
import java.util.List;

import br.com.btg.cegardenal.jokenpo.entity.JogadorEntity;

public final class JogadorSingleton {

    private static List<JogadorEntity> PLAYER_INSTANCE;
    private static String INFO = "Jogador Singleton Instance";

    private JogadorSingleton(){
    }

    public static List<JogadorEntity> getInstance() {
        if(PLAYER_INSTANCE == null) {
            PLAYER_INSTANCE = new ArrayList<JogadorEntity>();
        }
        return PLAYER_INSTANCE;
    }

    public static List<JogadorEntity> iniciar(){
        PLAYER_INSTANCE = new ArrayList<JogadorEntity>();
        return getInstance();
    }

    public String getInfo() {
        return this.INFO;
    }

}