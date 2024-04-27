package com.mx.sda.dto;

import org.hibernate.validator.constraints.NotBlank;

public class GrupoDto {
	
	@NotBlank
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
