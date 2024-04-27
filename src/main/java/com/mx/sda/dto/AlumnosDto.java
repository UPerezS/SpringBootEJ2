package com.mx.sda.dto;

import org.hibernate.validator.constraints.NotBlank;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;

public class AlumnosDto {
	
	@NotBlank
	private String nombre;
	@NotBlank
	private String numeroControl;
	@DecimalMin(value="0.01")
	private Float calificacion;
	@Min(value=1)
	private Integer grupo_id;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNumeroControl() {
		return numeroControl;
	}
	public void setNumeroControl(String numeroControl) {
		this.numeroControl = numeroControl;
	}
	public Float getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(Float calificacion) {
		this.calificacion = calificacion;
	}
	public Integer getGrupo_id() {
		return grupo_id;
	}
	public void setGrupo_id(Integer grupo_id) {
		this.grupo_id = grupo_id;
	}
	
}
