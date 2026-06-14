package com.ads3.auto_center.padroes.factory;

import com.ads3.auto_center.business.fornecedor.FornecedorDTO;
import com.ads3.auto_center.business.fornecedor.FornecedorModel;
import org.springframework.stereotype.Component;

@Component
public class FornecedorFactory implements IPessoaFactory<FornecedorModel, FornecedorDTO> {

    @Override
    public FornecedorModel criar(FornecedorDTO dto) {
        if (dto == null) return null;

        var entity = new FornecedorModel();
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