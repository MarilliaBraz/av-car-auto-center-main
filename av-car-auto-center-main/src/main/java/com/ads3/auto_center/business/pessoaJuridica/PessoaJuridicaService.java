package com.ads3.auto_center.business.pessoaJuridica;

import com.ads3.auto_center.core.exceptions.BusinessException;
import com.ads3.auto_center.core.services.GenericService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PessoaJuridicaService extends GenericService<PessoaJuridicaModel, IPessoaJuridicaRepository, PessoaJuridicaValidacao> {

    public PessoaJuridicaModel buscarPorCnpj(String cnpj) {
        return repository.findByCnpj(cnpj)
                .orElseThrow(() -> new BusinessException("Cliente (PJ) não encontrado para o CNPJ informado.", HttpStatus.NOT_FOUND));
    }

    @Override
    protected void beforeUpdate(PessoaJuridicaModel entity) {
        PessoaJuridicaModel existente = findByIdActive(entity.getId());
        if (entity.getDataCadastro() == null) {
            entity.setDataCadastro(existente.getDataCadastro());
        }
    }
}