package br.com.btg.cegardenal.jokenpo.service;

import java.util.List;

import br.com.btg.cegardenal.jokenpo.dto.JogadaRequest;
import br.com.btg.cegardenal.jokenpo.dto.JogadaResponse;
import br.com.btg.cegardenal.jokenpo.exception.JokenpoException;

public interface JogadaService {

    JogadaResponse insert(JogadaRequest move) throws JokenpoException;

    List<JogadaResponse> getAll() throws JokenpoException;

    List<JogadaResponse> deleteByName(String nome) throws JokenpoException;

    void clearAll();

}