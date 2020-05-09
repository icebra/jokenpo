package br.com.btg.cegardenal.jokenpo.service.impl;

import java.util.List;

import br.com.btg.cegardenal.jokenpo.dto.JogadorRequest;
import br.com.btg.cegardenal.jokenpo.dto.JogadorResponse;
import br.com.btg.cegardenal.jokenpo.entity.JogadorEntity;
import br.com.btg.cegardenal.jokenpo.exception.JokenpoException;

public interface JogadorServiceImpl {
	JogadorResponse insert(JogadorRequest player) throws JokenpoException;

	List<JogadorResponse> getAll() throws JokenpoException;

	JogadorEntity getEntityByName(String nome) throws JokenpoException;

	List<JogadorResponse> deleteByName(String nome) throws JokenpoException;

	void clearAll();
}