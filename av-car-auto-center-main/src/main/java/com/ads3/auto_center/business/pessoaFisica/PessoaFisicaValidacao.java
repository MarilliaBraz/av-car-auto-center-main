package com.ads3.auto_center.business.pessoaFisica;

import com.ads3.auto_center.core.exceptions.FieldValidationException;
import com.ads3.auto_center.core.exceptions.RuleValidationException;
import com.ads3.auto_center.core.validations.GenericValidation;
import org.springframework.stereotype.Component;

@Component
public class PessoaFisicaValidacao extends GenericValidation<PessoaFisicaModel, IPessoaFisicaRepository> {

    @Override
    public void validateFields(PessoaFisicaModel entity) {
        super.validateFields(entity);
        if (entity.getNome() == null || entity.getNome().isBlank()) {
            throw new FieldValidationException("nome", "Nome é obrigatório.");
        }
        if (entity.getCpf() == null || entity.getCpf().isBlank()) {
            throw new FieldValidationException("cpf", "CPF é obrigatório.");
        }
    }

    @Override
    public void validateInsert(PessoaFisicaModel entity) {
        if (repository.existsByCpf(entity.getCpf())) {
            throw new RuleValidationException("cpf-duplicado", "CPF já cadastrado.");
        }
    }

    @Override
    public void validateUpdate(PessoaFisicaModel entity) {
        repository.findByCpf(entity.getCpf()).ifPresent(found -> {
            if (!found.getId().equals(entity.getId())) {
                throw new RuleValidationException("cpf-duplicado", "CPF já cadastrado para outro cliente.");
            }
        });
    }
}