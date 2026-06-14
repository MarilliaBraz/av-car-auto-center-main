package com.ads3.auto_center.business.marca;

import com.ads3.auto_center.core.helpers.IGenericMapper;
import org.springframework.stereotype.Component;

@Component
public class MarcaMapper implements IGenericMapper<MarcaModel, MarcaDTO> {

    @Override
    public MarcaDTO toDto(MarcaModel entity) {
        if (entity == null) return null;
        MarcaDTO dto = new MarcaDTO();
        dto.setId(entity.getId());
        dto.setActive(entity.isAtivo());
        dto.setNomeMarca(entity.getNomeMarca());
        return dto;
    }

    @Override
    public MarcaModel toEntity(MarcaDTO dto) {
        if (dto == null) return null;
        MarcaModel entity = new MarcaModel();
        entity.setId(dto.getId());
        entity.setAtivo(dto.isActive());
        entity.setNomeMarca(dto.getNomeMarca());
        return entity;
    }
}