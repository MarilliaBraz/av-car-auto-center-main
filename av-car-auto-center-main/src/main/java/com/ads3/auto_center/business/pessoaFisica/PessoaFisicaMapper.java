package com.ads3.auto_center.business.pessoaFisica;

import com.ads3.auto_center.core.helpers.IGenericMapper;
import org.springframework.stereotype.Component;

@Component
public class PessoaFisicaMapper implements IGenericMapper<PessoaFisicaModel, PessoaFisicaDTO> {

    @Override
    public PessoaFisicaDTO toDto(PessoaFisicaModel entity) {
        if (entity == null) return null;
        PessoaFisicaDTO dto = new PessoaFisicaDTO();
        dto.setId(entity.getId());
        dto.setActive(entity.isAtivo());
        dto.setTelefone(entity.getTelefone());
        dto.setEmail(entity.getEmail());
        dto.setEndereco(entity.getEndereco());
        dto.setDataCadastro(entity.getDataCadastro());
        dto.setCpf(entity.getCpf());
        dto.setNome(entity.getNome());
        dto.setDataNascimento(entity.getDataNascimento());
        return dto;
    }

    @Override
    public PessoaFisicaModel toEntity(PessoaFisicaDTO dto) {
        if (dto == null) return null;
        PessoaFisicaModel entity = new PessoaFisicaModel();
        entity.setId(dto.getId());
        entity.setAtivo(dto.isActive());
        entity.setTelefone(dto.getTelefone());
        entity.setEmail(dto.getEmail());
        entity.setEndereco(dto.getEndereco());
        entity.setDataCadastro(dto.getDataCadastro());
        entity.setCpf(dto.getCpf());
        entity.setNome(dto.getNome());
        entity.setDataNascimento(dto.getDataNascimento());
        return entity;
    }
}