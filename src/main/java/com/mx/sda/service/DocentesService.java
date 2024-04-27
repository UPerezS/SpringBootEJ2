package com.mx.sda.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mx.sda.dto.DocentesDto;

public interface DocentesService {
	
	Page<DocentesDto> listarDocentes(Pageable pageable);

	DocentesDto buscarDocentePorId(Long cve_Docente);

    DocentesDto guardarDocente(DocentesDto docenteDto);

    DocentesDto editarDocente(Long cve_Docente, DocentesDto docenteDto);

    void eliminarDocente(Long cve_Docente);
   
}
