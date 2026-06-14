package com.ads3.auto_center.business.pessoaJuridica;

import com.ads3.auto_center.core.helpers.IGenericMapper;
import org.springframework.stereotype.Component;

@Component
public class PessoaJuridicaMapper implements IGenericMapper<PessoaJuridicaModel, PessoaJuridicaDTO> {

    @Override
    public PessoaJuridicaDTO toDto(PessoaJuridicaModel entity) {
        if (entity == null) return null;
        PessoaJuridicaDTO dto = new PessoaJuridicaDTO();
        dto.setId(entity.getId());
        dto.setActive(entity.isAtivo());
        dto.setTelefone(entity.getTelefone());
        dto.setEmail(entity.getEmail());
        dto.setEndereco(entity.getEndereco());
        dto.setDataCadastro(entity.getDataCadastro());
        dto.setCnpj(entity.getCnpj());
        dto.setRazaoSocial(entity.getRazaoSocial());
        dto.setNomeFantasia(entity.getNomeFantasia());
        return dto;
    }

    @Override
    public PessoaJuridicaModel toEntity(PessoaJuridicaDTO dto) {
        if (dto == null) return null;
        PessoaJuridicaModel entity = new PessoaJuridicaModel();
        entity.setId(dto.getId());
        entity.setAtivo(dto.isActive());
        entity.setTelefone(dto.getTelefone());
        entity.setEmail(dto.getEmail());
        entity.setEndereco(dto.getEndereco());
        entity.setDataCadastro(dto.getDataCadastro());
        entity.setCnpj(dto.getCnpj());
        entity.setRazaoSocial(dto.getRazaoSocial());
        entity.setNomeFantasia(dto.getNomeFantasia());
        return entity;
    }
}