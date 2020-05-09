package br.com.btg.cegardenal.jokenpo.dto.api;

import java.sql.Timestamp;

public class ApiTimestamp {
	private Timestamp timestamp;

	public ApiTimestamp() {
	}

	public ApiTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

}