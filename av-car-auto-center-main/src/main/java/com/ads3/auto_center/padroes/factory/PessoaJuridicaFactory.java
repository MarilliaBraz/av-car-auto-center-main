package com.ads3.auto_center.padroes.factory;

import com.ads3.auto_center.business.pessoaJuridica.PessoaJuridicaDTO;
import com.ads3.auto_center.business.pessoaJuridica.PessoaJuridicaModel;
import org.springframework.stereotype.Component;

@Component
public class PessoaJuridicaFactory implements IPessoaFactory<PessoaJuridicaModel, PessoaJuridicaDTO> {

    @Override
    public PessoaJuridicaModel criar(PessoaJuridicaDTO dto) {
        if (dto == null) return null;

        var entity = new PessoaJuridicaModel();
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