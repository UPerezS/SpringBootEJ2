package com.mx.sda.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mx.sda.dto.AlumnosDto;
import com.mx.sda.persistencia.entity.Alumnos;

public interface AlumnosService {

    Page<Alumnos> findAll(Pageable pageable);

    Optional<Alumnos> findOneById(Integer alumnoId);

    Alumnos createOne(AlumnosDto alumnosDto);

    Alumnos updateOneById(Integer alumnoId, AlumnosDto alumnosDto);

    Alumnos disableOneById(Integer alumnoId);

}
