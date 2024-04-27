package com.mx.sda.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.sda.dto.DocentesDto;
import com.mx.sda.exception.ObjectNotFoundException;
import com.mx.sda.persistencia.entity.Docentes;
import com.mx.sda.service.DocentesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/docentes")
public class DocentesController {

	private final DocentesService docentesService;

    @Autowired
    public DocentesController(DocentesService docentesService) {
        this.docentesService = docentesService;
    }

    @GetMapping
    public ResponseEntity<Page<DocentesDto>> listarDocentes(Pageable pageable) {
        Page<DocentesDto> docentes = docentesService.listarDocentes(pageable);
        return ResponseEntity.ok(docentes);
    }

    @GetMapping("/{cve_Docente}")
    public ResponseEntity<DocentesDto> buscarDocentePorId(@PathVariable("cve_Docente") Long cveDocente) {
        DocentesDto docente = docentesService.buscarDocentePorId(cveDocente);
        return ResponseEntity.ok(docente);
    }

    @PostMapping
    public ResponseEntity<DocentesDto> guardarDocente(@Valid @RequestBody DocentesDto docenteDto) {
        DocentesDto nuevoDocente = docentesService.guardarDocente(docenteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDocente);
    }

    @PutMapping("/{cve_Docente}")
    public ResponseEntity<DocentesDto> editarDocente(@PathVariable("cve_Docente") Long cveDocente,@Valid @RequestBody DocentesDto docenteDto) {
        DocentesDto docenteActualizado = docentesService.editarDocente(cveDocente, docenteDto);
        return ResponseEntity.ok(docenteActualizado);
    }

    @DeleteMapping("/{cve_Docente}")
    public ResponseEntity<Void> eliminarDocente(@PathVariable("cve_Docente") Long cveDocente) {
        docentesService.eliminarDocente(cveDocente);
        return ResponseEntity.noContent().build();
    }
    
}
