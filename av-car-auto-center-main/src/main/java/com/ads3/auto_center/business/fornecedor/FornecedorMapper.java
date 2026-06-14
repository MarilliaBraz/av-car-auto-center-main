package com.ads3.auto_center.business.fornecedor;

import com.ads3.auto_center.core.helpers.IGenericMapper;
import org.springframework.stereotype.Component;

@Component
public class FornecedorMapper implements IGenericMapper<FornecedorModel, FornecedorDTO> {

    @Override
    public FornecedorDTO toDto(FornecedorModel entity) {
        if (entity == null) return null;
        FornecedorDTO dto = new FornecedorDTO();
        dto.setId(entity.getId());
        dto.setActive(entity.isAtivo());
        dto.setNomeFornecedor(entity.getNomeFornecedor());
        dto.setCnpj(entity.getCnpj());
        dto.setTelefone(entity.getTelefone());
        dto.setEmail(entity.getEmail());
        dto.setEndereco(entity.getEndereco());
        return dto;
    }

    @Override
    public FornecedorModel toEntity(FornecedorDTO dto) {
        if (dto == null) return null;
        FornecedorModel entity = new FornecedorModel();
        entity.setId(dto.getId());
        entity.setAtivo(dto.isActive());
        entity.setNomeFornecedor(dto.getNomeFornecedor());
        entity.setCnpj(dto.getCnpj());
        entity.setTelefone(dto.getTelefone());
        entity.setEmail(dto.getEmail());
        entity.setEndereco(dto.getEndereco());
        return entity;
    }
}