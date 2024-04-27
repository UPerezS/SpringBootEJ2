package com.mx.sda.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class GuardarUsuario implements Serializable {
	
	@NotBlank
	@Size(min = 4, max = 20)
	private String nombre;
	
	@NotBlank
	private String username;
	
	@NotBlank
	@Size(min = 8, max = 20)
	private String password;
	
	@NotBlank
	@Size(min = 8, max = 20)
	private String repitePassword;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepitePassword() {
		return repitePassword;
	}

	public void setRepitePassword(String repitePassword) {
		this.repitePassword = repitePassword;
	}
	
}
