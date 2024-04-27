package com.mx.sda.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mx.sda.dto.AlumnosDto;
import com.mx.sda.exception.ObjectNotFoundException;
import com.mx.sda.persistencia.entity.Alumnos;
import com.mx.sda.persistencia.entity.Alumnos.AlumnoEstatus;
import com.mx.sda.persistencia.entity.Grupo;
import com.mx.sda.persistencia.repository.AlumnosRepository;
import com.mx.sda.service.AlumnosService;

@Service
public class AlumnosServiceImpl implements AlumnosService {
	
	@Autowired
	private AlumnosRepository alumnosRepository;
	
	@Override
	public Page<Alumnos> findAll(Pageable pageable) {
		return alumnosRepository.findAll(pageable);
	}

	@Override
	public Optional<Alumnos> findOneById(Integer alumnoId) {
		return alumnosRepository.findById(alumnoId);
	}

	@Override
	public Alumnos createOne(AlumnosDto alumnosDto) {
		Alumnos alumnos = new Alumnos();
		alumnos.setNombre(alumnosDto.getNombre());
		alumnos.setNumeroControl(alumnosDto.getNumeroControl());
		alumnos.setCalificacion(alumnos.getCalificacion());
		alumnos.setEstatus(AlumnoEstatus.REGULAR);
		
		Grupo grupo = new Grupo();
		grupo.setId(alumnosDto.getGrupo_id());
		
		alumnos.setGrupo(grupo);
		
		return alumnosRepository.save(alumnos);
	}

	@Override
	public Alumnos updateOneById(Integer alumnoId, AlumnosDto alumnosDto) {
		Alumnos alumnoBd = alumnosRepository.findById(alumnoId)
				.orElseThrow(()-> new ObjectNotFoundException("No se Encuentra el Alumno"));
		
		alumnoBd.setNombre(alumnosDto.getNombre());
		alumnoBd.setNumeroControl(alumnosDto.getNumeroControl());
		alumnoBd.setCalificacion(alumnosDto.getCalificacion());
		
		Grupo grupo = new Grupo();
		grupo.setId(alumnosDto.getGrupo_id());
		alumnoBd.setGrupo(grupo);
		return alumnosRepository.save(alumnoBd);
	}

	@Override
	public Alumnos disableOneById(Integer alumnoId) {
		Alumnos alumnoBd = alumnosRepository.findById(alumnoId)
				.orElseThrow(()-> new ObjectNotFoundException("No se Encuentra el Alumno"));
		
		alumnoBd.setEstatus(AlumnoEstatus.DESHABILITADO);
		return alumnosRepository.save(alumnoBd);
	}
}
