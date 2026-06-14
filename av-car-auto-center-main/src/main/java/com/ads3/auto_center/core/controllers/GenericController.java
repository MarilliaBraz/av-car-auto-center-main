package com.ads3.auto_center.core.controllers;

import com.ads3.auto_center.core.domains.BaseModel;
import com.ads3.auto_center.core.dtos.BaseDTO;
import com.ads3.auto_center.core.helpers.IGenericMapper;
import com.ads3.auto_center.core.services.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class GenericController<
        E extends BaseModel,
        D extends BaseDTO,
        S extends IGenericService<E, ?, ?>,
        M extends IGenericMapper<E, D>> {

    @Autowired
    protected S service;

    @Autowired
    protected M mapper;

    @GetMapping("/{id}")
    public ResponseEntity<D> findById(@PathVariable Long id) {
        E entity = service.findByIdActive(id);
        return ResponseEntity.ok(mapper.toDto(entity));
    }

    @GetMapping
    public ResponseEntity<Page<D>> findAll(Pageable pageable) {
        Page<E> entities = service.findAllActive(pageable);
        return ResponseEntity.ok(mapper.toDtoPage(entities));
    }

    @PostMapping
    public ResponseEntity<D> insert(@RequestBody D dto) {
        E entity = mapper.toEntity(dto);
        E saved = service.insert(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<D> update(@PathVariable Long id, @RequestBody D dto) {
        E entity = mapper.toEntity(dto);
        entity.setId(id);
        E original = service.findByIdActive(id);
        entity.setAtivo(original.isAtivo());
        entity.setDataHoraCriacao(original.getDataHoraCriacao());
        E updated = service.update(entity);
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}