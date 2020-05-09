package br.com.btg.cegardenal.jokenpo.util;

import java.util.ArrayList;
import java.util.List;

import br.com.btg.cegardenal.jokenpo.entity.JogadaEntity;

public final class JogadaSingleton {

    private static List<JogadaEntity> MOVE_INSTANCE;
    private static String INFO = "Instancia singleton da jogada";

    private JogadaSingleton(){
    }

    public static List<JogadaEntity> getInstance() {
        if(MOVE_INSTANCE == null) {
            MOVE_INSTANCE = new ArrayList<JogadaEntity>();
        }
        return MOVE_INSTANCE;
    }

    public static List<JogadaEntity> iniciar(){
        MOVE_INSTANCE = new ArrayList<JogadaEntity>();
        return getInstance();
    }

    public String getInfo() {
        return this.INFO;
    }

}