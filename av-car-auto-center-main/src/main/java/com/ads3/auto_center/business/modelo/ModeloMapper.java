package com.ads3.auto_center.business.modelo;

import com.ads3.auto_center.business.marca.MarcaModel;
import com.ads3.auto_center.core.helpers.IGenericMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component
public class ModeloMapper implements IGenericMapper<ModeloModel, ModeloDTO> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public ModeloDTO toDto(ModeloModel entity) {
        if (entity == null) return null;
        ModeloDTO dto = new ModeloDTO();
        dto.setId(entity.getId());
        dto.setActive(entity.isAtivo());
        dto.setNomeModelo(entity.getNomeModelo());
        if (entity.getMarca() != null) dto.setIdMarca(entity.getMarca().getId());
        return dto;
    }

    @Override
    public ModeloModel toEntity(ModeloDTO dto) {
        if (dto == null) return null;
        ModeloModel entity = new ModeloModel();
        entity.setId(dto.getId());
        entity.setAtivo(dto.isActive());
        entity.setNomeModelo(dto.getNomeModelo());
        if (dto.getIdMarca() != null) {
            entity.setMarca(em.getReference(MarcaModel.class, dto.getIdMarca()));
        }
        return entity;
    }
}