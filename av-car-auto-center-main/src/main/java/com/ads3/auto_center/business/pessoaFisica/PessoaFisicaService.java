package com.ads3.auto_center.business.pessoaFisica;

import com.ads3.auto_center.core.exceptions.BusinessException;
import com.ads3.auto_center.core.services.GenericService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PessoaFisicaService extends GenericService<PessoaFisicaModel, IPessoaFisicaRepository, PessoaFisicaValidacao> {

    public PessoaFisicaModel buscarPorCpf(String cpf) {
        return repository.findByCpf(cpf)
                .orElseThrow(() -> new BusinessException("Cliente (PF) não encontrado para o CPF informado.", HttpStatus.NOT_FOUND));
    }

    @Override
    protected void beforeUpdate(PessoaFisicaModel entity) {
        PessoaFisicaModel existente = findByIdActive(entity.getId());
        if (entity.getDataCadastro() == null) {
            entity.setDataCadastro(existente.getDataCadastro());
        }
    }
}