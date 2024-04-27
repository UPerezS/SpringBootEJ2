package com.mx.sda.persistencia.entity;

import com.mx.sda.persistencia.entity.Grupo.GrupoEstatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Grupo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	@Enumerated(EnumType.STRING)
	private GrupoEstatus estatus;
	
	public static enum GrupoEstatus{
		HABILITADO,DESHABILITADO
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public GrupoEstatus getEstatus() {
		return estatus;
	}

	public void setEstatus(GrupoEstatus estatus) {
		this.estatus = estatus;
	}
	
}
