package br.com.btg.cegardenal.jokenpo.dto.api;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ApiResponse<T> {

	private ApiTimestamp apiTimestamp;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T data;

	public ApiResponse() {
	}

	public ApiResponse(T data) {
		this.apiTimestamp = new ApiTimestamp(new Timestamp(System.currentTimeMillis()));
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ApiTimestamp getApiTimestamp() {
		return apiTimestamp;
	}

	public void setApiTimestamp(ApiTimestamp apiTimestamp) {
		this.apiTimestamp = apiTimestamp;
	}

}