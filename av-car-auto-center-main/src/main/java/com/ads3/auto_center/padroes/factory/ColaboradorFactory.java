package com.ads3.auto_center.padroes.factory;

import com.ads3.auto_center.business.colaborador.ColaboradorDTO;
import com.ads3.auto_center.business.colaborador.ColaboradorModel;
import org.springframework.stereotype.Component;

@Component
public class ColaboradorFactory implements IPessoaFactory<ColaboradorModel, ColaboradorDTO> {

    @Override
    public ColaboradorModel criar(ColaboradorDTO dto) {
        if (dto == null) return null;

        var entity = new ColaboradorModel();
        entity.setId(dto.getId());
        entity.setAtivo(dto.isActive());
        entity.setTelefone(dto.getTelefone());
        entity.setEmail(dto.getEmail());
        entity.setEndereco(dto.getEndereco());
        entity.setNome(dto.getNome());
        entity.setCpf(dto.getCpf());
        entity.setDataAdmissao(dto.getDataAdmissao());
        entity.setSalario(dto.getSalario());

        return entity;
    }
}