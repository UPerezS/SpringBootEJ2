package com.mx.sda.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mx.sda.dto.GrupoDto;
import com.mx.sda.exception.ObjectNotFoundException;
import com.mx.sda.persistencia.entity.Grupo;
import com.mx.sda.persistencia.entity.Grupo.GrupoEstatus;
import com.mx.sda.persistencia.repository.GruposRepository;
import com.mx.sda.service.GruposService;

@Service
public class GruposServiceImpl implements GruposService {
    
    @Autowired
    private GruposRepository gruposRepository;
    
    @Override
    public Page<Grupo> findAll(Pageable pageable) {
        return gruposRepository.findAll(pageable);
    }

    @Override
    public Optional<Grupo> findOneById(Integer grupoId) {
        return gruposRepository.findById(grupoId);
    }

    @Override
    public Grupo createOne(GrupoDto grupoDto) {
        Grupo grupo = new Grupo();
        grupo.setNombre(grupoDto.getNombre());
        grupo.setEstatus(GrupoEstatus.HABILITADO);

        return gruposRepository.save(grupo);
    }

    @Override
    public Grupo updateOneById(Integer grupoId, GrupoDto grupoDto) {
        Grupo grupoBd = gruposRepository.findById(grupoId)
                .orElseThrow(() -> new ObjectNotFoundException("No se Encuentra el Grupo"));

        grupoBd.setNombre(grupoDto.getNombre());

        return gruposRepository.save(grupoBd);
    }

    @Override
    public Grupo disableOneById(Integer grupoId) {
        Grupo grupoBd = gruposRepository.findById(grupoId)
                .orElseThrow(() -> new ObjectNotFoundException("No se Encuentra el Grupo"));

        grupoBd.setEstatus(GrupoEstatus.DESHABILITADO);

        return gruposRepository.save(grupoBd);
    }
    
}
