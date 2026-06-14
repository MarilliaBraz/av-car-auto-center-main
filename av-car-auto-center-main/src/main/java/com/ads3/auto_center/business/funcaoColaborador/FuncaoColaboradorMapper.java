package com.ads3.auto_center.business.funcaoColaborador;

import com.ads3.auto_center.core.helpers.IGenericMapper;
import org.springframework.stereotype.Component;

@Component
public class FuncaoColaboradorMapper implements IGenericMapper<FuncaoColaboradorModel, FuncaoColaboradorDTO> {

    @Override
    public FuncaoColaboradorDTO toDto(FuncaoColaboradorModel entity) {
        if (entity == null) return null;
        FuncaoColaboradorDTO dto = new FuncaoColaboradorDTO();
        dto.setId(entity.getId());
        dto.setActive(entity.isAtivo());
        dto.setNomeFuncao(entity.getNomeFuncao());
        dto.setDescricao(entity.getDescricao());
        return dto;
    }

    @Override
    public FuncaoColaboradorModel toEntity(FuncaoColaboradorDTO dto) {
        if (dto == null) return null;
        FuncaoColaboradorModel entity = new FuncaoColaboradorModel();
        entity.setId(dto.getId());
        entity.setAtivo(dto.isActive());
        entity.setNomeFuncao(dto.getNomeFuncao());
        entity.setDescricao(dto.getDescricao());
        return entity;
    }
}