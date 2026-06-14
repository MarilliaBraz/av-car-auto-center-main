package com.ads3.auto_center.padroes.factory;

import com.ads3.auto_center.business.pessoaFisica.PessoaFisicaDTO;
import com.ads3.auto_center.business.pessoaFisica.PessoaFisicaModel;
import org.springframework.stereotype.Component;

@Component
public class PessoaFisicaFactory implements IPessoaFactory<PessoaFisicaModel, PessoaFisicaDTO> {

    @Override
    public PessoaFisicaModel criar(PessoaFisicaDTO dto) {
        if (dto == null) return null;

        var entity = new PessoaFisicaModel();
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