package br.com.btg.cegardenal.jokenpo.service;

import java.util.List;

import br.com.btg.cegardenal.jokenpo.dto.JogadorResponse;
import br.com.btg.cegardenal.jokenpo.dto.JokenpoResponse;
import br.com.btg.cegardenal.jokenpo.exception.JokenpoException;

public interface JokenpoService {
	List<JogadorResponse> iniciar() throws JokenpoException;

	JokenpoResponse jokenpo() throws JokenpoException;
}