package com.ads3.auto_center.business.servicoExterno;

import com.ads3.auto_center.core.helpers.IGenericMapper;
import org.springframework.stereotype.Component;

@Component
public class ServicoExternoMapper implements IGenericMapper<ServicoExternoModel, ServicoExternoDTO> {

    @Override
    public ServicoExternoDTO toDto(ServicoExternoModel entity) {
        if (entity == null) return null;
        ServicoExternoDTO dto = new ServicoExternoDTO();
        dto.setId(entity.getId());
        dto.setActive(entity.isAtivo());
        dto.setNomeServico(entity.getNomeServico());
        dto.setDescricao(entity.getDescricao());
        dto.setPrecoBase(entity.getPrecoBase());
        return dto;
    }

    @Override
    public ServicoExternoModel toEntity(ServicoExternoDTO dto) {
        if (dto == null) return null;
        ServicoExternoModel entity = new ServicoExternoModel();
        entity.setId(dto.getId());
        entity.setAtivo(dto.isActive());
        entity.setNomeServico(dto.getNomeServico());
        entity.setDescricao(dto.getDescricao());
        entity.setPrecoBase(dto.getPrecoBase());
        return entity;
    }
}