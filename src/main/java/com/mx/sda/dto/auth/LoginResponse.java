package com.mx.sda.dto.auth;

import java.io.Serializable;

public class LoginResponse implements Serializable {

	private String jwt;

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	
}
