package com.ads3.auto_center.business.servicoInterno;

import com.ads3.auto_center.core.helpers.IGenericMapper;
import org.springframework.stereotype.Component;

@Component
public class ServicoInternoMapper implements IGenericMapper<ServicoInternoModel, ServicoInternoDTO> {

    @Override
    public ServicoInternoDTO toDto(ServicoInternoModel entity) {
        if (entity == null) return null;
        ServicoInternoDTO dto = new ServicoInternoDTO();
        dto.setId(entity.getId());
        dto.setActive(entity.isAtivo());
        dto.setNomeServico(entity.getNomeServico());
        dto.setDescricao(entity.getDescricao());
        dto.setPrecoBase(entity.getPrecoBase());
        dto.setTempoEstimadoMin(entity.getTempoEstimadoMin());
        return dto;
    }

    @Override
    public ServicoInternoModel toEntity(ServicoInternoDTO dto) {
        if (dto == null) return null;
        ServicoInternoModel entity = new ServicoInternoModel();
        entity.setId(dto.getId());
        entity.setAtivo(dto.isActive());
        entity.setNomeServico(dto.getNomeServico());
        entity.setDescricao(dto.getDescricao());
        entity.setPrecoBase(dto.getPrecoBase());
        entity.setTempoEstimadoMin(dto.getTempoEstimadoMin());
        return entity;
    }
}