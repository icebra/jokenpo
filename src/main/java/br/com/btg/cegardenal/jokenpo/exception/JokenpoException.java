package br.com.btg.cegardenal.jokenpo.exception;

import br.com.btg.cegardenal.jokenpo.enumeration.EnumException;

public class JokenpoException extends Exception {
	private static final long serialVersionUID = -187273214423360704L;

	public JokenpoException(EnumException enumException) {
		super(enumException.getCode() + " - " + enumException.getMessage());
	}

	public JokenpoException(String errorMessage) {
		super(errorMessage);
	}

}