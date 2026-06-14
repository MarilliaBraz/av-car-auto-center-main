package com.ads3.auto_center.business.pessoaJuridica;

import com.ads3.auto_center.core.exceptions.FieldValidationException;
import com.ads3.auto_center.core.exceptions.RuleValidationException;
import com.ads3.auto_center.core.validations.GenericValidation;
import org.springframework.stereotype.Component;

@Component
public class PessoaJuridicaValidacao extends GenericValidation<PessoaJuridicaModel, IPessoaJuridicaRepository> {

    @Override
    public void validateFields(PessoaJuridicaModel entity) {
        super.validateFields(entity);
        if (entity.getCnpj() == null || entity.getCnpj().isBlank()) {
            throw new FieldValidationException("cnpj", "CNPJ é obrigatório.");
        }
        if (entity.getRazaoSocial() == null || entity.getRazaoSocial().isBlank()) {
            throw new FieldValidationException("razaoSocial", "Razão social é obrigatória.");
        }
    }

    @Override
    public void validateInsert(PessoaJuridicaModel entity) {
        if (repository.existsByCnpj(entity.getCnpj())) {
            throw new RuleValidationException("cnpj-duplicado", "CNPJ já cadastrado.");
        }
    }

    @Override
    public void validateUpdate(PessoaJuridicaModel entity) {
        repository.findByCnpj(entity.getCnpj()).ifPresent(found -> {
            if (!found.getId().equals(entity.getId())) {
                throw new RuleValidationException("cnpj-duplicado", "CNPJ já cadastrado para outro cliente.");
            }
        });
    }
}