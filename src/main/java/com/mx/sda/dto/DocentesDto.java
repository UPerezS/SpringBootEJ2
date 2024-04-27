package com.mx.sda.dto;

import com.mx.sda.persistencia.entity.Docentes;
import com.mx.sda.persistencia.entity.Docentes.TipoDocente;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class DocentesDto {
    private Long cveDocente;
    
    @Size(min = 10, max = 80, message = "El nombre debe tener entre 10 y 80 caracteres")
    private String nombre;
    
    @Size(min = 20, max = 80, message = "La carrera debe tener entre 20 y 80 caracteres")
    private String carrera;
    
    @PastOrPresent(message = "La fecha de registro debe ser igual o anterior a la fecha actual")
    private LocalDateTime fechaRegistro;
    
    @NotNull(message = "El tipo de docente es obligatorio")
    private TipoDocente tipoDocente;
    
	public Long getCveDocente() {
		return cveDocente;
	}
	public void setCveDocente(Long cveDocente) {
		this.cveDocente = cveDocente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public TipoDocente getTipoDocente() {
		return tipoDocente;
	}
	public void setTipoDocente(TipoDocente tipoDocente) {
		this.tipoDocente = tipoDocente;
	}
}
