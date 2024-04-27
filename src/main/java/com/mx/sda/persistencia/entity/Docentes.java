package com.mx.sda.persistencia.entity;

import java.time.LocalDateTime;

import com.mx.sda.persistencia.entity.Docentes.TipoDocente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
public class Docentes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cveDocente")
    private Long cveDocente;
	
	@Size(min = 10, max = 80, message = "El nombre debe tener entre 10 y 80 caracteres")
    private String nombre;
	
	@Size(min = 20, max = 80, message = "La carrera debe tener entre 20 y 80 caracteres")
    private String carrera;

    @Enumerated
    private TipoDocente tipoDocente;
    
    public static enum TipoDocente{
    	PROFESOR_DE_ASIGNATURA,
        PROFESOR_DE_TIEMPO_COMPLETO,
        FUNCIONARIO_DOCENTE
	}
    
    @PastOrPresent(message = "La fecha de registro debe ser igual o anterior a la fecha actual")
    private LocalDateTime fechaRegistro;

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

    public TipoDocente getTipoDocente() {
        return tipoDocente;
    }

    public void setTipoDocente(TipoDocente tipoDocente) {
        this.tipoDocente = tipoDocente;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
}
