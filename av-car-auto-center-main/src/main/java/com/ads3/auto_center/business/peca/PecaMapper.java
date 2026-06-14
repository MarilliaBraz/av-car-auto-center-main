package com.ads3.auto_center.business.peca;

import com.ads3.auto_center.core.helpers.IGenericMapper;
import org.springframework.stereotype.Component;

@Component
public class PecaMapper implements IGenericMapper<PecaModel, PecaDTO> {

    @Override
    public PecaDTO toDto(PecaModel entity) {
        if (entity == null) return null;
        PecaDTO dto = new PecaDTO();
        dto.setId(entity.getId());
        dto.setActive(entity.isAtivo());
        dto.setNomePeca(entity.getNomePeca());
        dto.setCodigoRef(entity.getCodigoRef());
        dto.setPrecoUnitario(entity.getPrecoUnitario());
        dto.setEstoqueAtual(entity.getEstoqueAtual());
        return dto;
    }

    @Override
    public PecaModel toEntity(PecaDTO dto) {
        if (dto == null) return null;
        PecaModel entity = new PecaModel();
        entity.setId(dto.getId());
        entity.setAtivo(dto.isActive());
        entity.setNomePeca(dto.getNomePeca());
        entity.setCodigoRef(dto.getCodigoRef());
        entity.setPrecoUnitario(dto.getPrecoUnitario());
        entity.setEstoqueAtual(dto.getEstoqueAtual() != null ? dto.getEstoqueAtual() : 0);
        return entity;
    }
}