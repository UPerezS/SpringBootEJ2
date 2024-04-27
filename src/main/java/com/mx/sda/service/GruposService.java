package com.mx.sda.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mx.sda.dto.GrupoDto;
import com.mx.sda.persistencia.entity.Grupo;

public interface GruposService {
	
	Page<Grupo> findAll(Pageable pageable);
	
	Optional<Grupo> findOneById(Integer grupoId);
	
	Grupo createOne(GrupoDto grupoDto);
	
	Grupo updateOneById(Integer grupoId, GrupoDto grupoDto);
	
	Grupo disableOneById(Integer grupoId);
	
}
