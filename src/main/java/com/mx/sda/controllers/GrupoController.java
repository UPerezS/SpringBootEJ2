package com.mx.sda.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mx.sda.dto.GrupoDto;
import com.mx.sda.persistencia.entity.Grupo;
import com.mx.sda.service.GruposService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GruposService gruposService;

    @GetMapping
    public ResponseEntity<Page<Grupo>> findAll(Pageable pageable) {
        Page<Grupo> gruposPage = gruposService.findAll(pageable);
        if (gruposPage.hasContent()) {
            return ResponseEntity.ok(gruposPage);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{grupoId}")
    public ResponseEntity<Grupo> findOneById(@PathVariable Integer grupoId) {
        Optional<Grupo> grupo = gruposService.findOneById(grupoId);
        if (grupo.isPresent()) {
            return ResponseEntity.ok(grupo.get());
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public ResponseEntity<Grupo> createOne(@RequestBody @Valid GrupoDto grupoDto) {
        Grupo grupo = gruposService.createOne(grupoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(grupo);
    }

    @PutMapping("/{grupoId}")
    public ResponseEntity<Grupo> updateOneById(@PathVariable Integer grupoId, @RequestBody @Valid GrupoDto grupoDto) {
        Grupo grupo = gruposService.updateOneById(grupoId, grupoDto);
        return ResponseEntity.ok(grupo);
    }

    @PutMapping("/{grupoId}/disabled")
    public ResponseEntity<Grupo> disableOneById(@PathVariable Integer grupoId) {
        Grupo grupo = gruposService.disableOneById(grupoId);
        return ResponseEntity.ok(grupo);
    }
}
