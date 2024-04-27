package com.mx.sda.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mx.sda.dto.DocentesDto;
import com.mx.sda.exception.ObjectNotFoundException;
import com.mx.sda.persistencia.entity.Docentes;
import com.mx.sda.persistencia.entity.Docentes.TipoDocente;
import com.mx.sda.persistencia.repository.DocentesRepository;
import com.mx.sda.service.DocentesService;

@Service
public class DocentesServiceImpl implements DocentesService {

	private final DocentesRepository docentesRepository;

    @Autowired
    public DocentesServiceImpl(DocentesRepository docentesRepository) {
        this.docentesRepository = docentesRepository;
    }

    @Override
    public Page<DocentesDto> listarDocentes(Pageable pageable) {
        return docentesRepository.findAll(pageable)
                .map(this::mapToDto);
    }

    @Override
    public DocentesDto buscarDocentePorId(Long cve_Docente) {
        Docentes docente = docentesRepository.findById(cve_Docente)
                .orElseThrow(() -> new ObjectNotFoundException("No se encontró el docente con el ID: " + cve_Docente));
        return mapToDto(docente);
    }

    @Override
    public DocentesDto guardarDocente(DocentesDto docenteDto) {
        Docentes docente = mapToEntity(docenteDto);
        docente = docentesRepository.save(docente);
        return mapToDto(docente);
    }

    @Override
    public DocentesDto editarDocente(Long cve_Docente, DocentesDto docenteDto) {
        Docentes docente = docentesRepository.findById(cve_Docente)
                .orElseThrow(() -> new ObjectNotFoundException("No se encontró el docente con el ID: " + cve_Docente));

        docente.setNombre(docenteDto.getNombre());
        docente.setCarrera(docenteDto.getCarrera());
        docente.setTipoDocente(docenteDto.getTipoDocente());
        docente.setFechaRegistro(docenteDto.getFechaRegistro());

        docente = docentesRepository.save(docente);
        return mapToDto(docente);
    }

    @Override
    public void eliminarDocente(Long cve_Docente) {
        if (!docentesRepository.existsById(cve_Docente)) {
            throw new ObjectNotFoundException("No se encontró el docente con el ID: " + cve_Docente);
        }
        docentesRepository.deleteById(cve_Docente);
    }

    // Método para mapear entidad a DTO
    private DocentesDto mapToDto(Docentes docente) {
        DocentesDto docenteDto = new DocentesDto();
        docenteDto.setCveDocente(docente.getCveDocente());
        docenteDto.setNombre(docente.getNombre());
        docenteDto.setCarrera(docente.getCarrera());
        docenteDto.setTipoDocente(docente.getTipoDocente());
        docenteDto.setFechaRegistro(docente.getFechaRegistro());
        return docenteDto;
    }

    // Método para mapear DTO a entidad
    private Docentes mapToEntity(DocentesDto docenteDto) {
        Docentes docente = new Docentes();
        docente.setCveDocente(docenteDto.getCveDocente());
        docente.setNombre(docenteDto.getNombre());
        docente.setCarrera(docenteDto.getCarrera());
        docente.setTipoDocente(docenteDto.getTipoDocente());
        docente.setFechaRegistro(docenteDto.getFechaRegistro());
        return docente;
    }
    
}
