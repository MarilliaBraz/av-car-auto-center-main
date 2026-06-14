package com.ads3.auto_center.business.colaborador;

import com.ads3.auto_center.core.exceptions.FieldValidationException;
import com.ads3.auto_center.core.exceptions.RuleValidationException;
import com.ads3.auto_center.core.validations.GenericValidation;
import org.springframework.stereotype.Component;

@Component
public class ColaboradorValidacao extends GenericValidation<ColaboradorModel, IColaboradorRepository> {

    @Override
    public void validateFields(ColaboradorModel entity) {
        super.validateFields(entity);
        if (entity.getNome() == null || entity.getNome().isBlank()) {
            throw new FieldValidationException("nome", "Nome é obrigatório.");
        }
        if (entity.getCpf() == null || entity.getCpf().isBlank()) {
            throw new FieldValidationException("cpf", "CPF é obrigatório.");
        }
        if (entity.getDataAdmissao() == null) {
            throw new FieldValidationException("dataAdmissao", "Data de admissão é obrigatória.");
        }
    }

    @Override
    public void validateInsert(ColaboradorModel entity) {
        if (repository.existsByCpf(entity.getCpf())) {
            throw new RuleValidationException("cpf-duplicado", "CPF já cadastrado para outro colaborador.");
        }
    }
}