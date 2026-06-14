package com.ads3.auto_center.business.marca;

import com.ads3.auto_center.core.exceptions.FieldValidationException;
import com.ads3.auto_center.core.exceptions.RuleValidationException;
import com.ads3.auto_center.core.validations.GenericValidation;
import org.springframework.stereotype.Component;

@Component
public class MarcaValidacao extends GenericValidation<MarcaModel, IMarcaRepository> {

    @Override
    public void validateFields(MarcaModel entity) {
        super.validateFields(entity);
        if (entity.getNomeMarca() == null || entity.getNomeMarca().isBlank()) {
            throw new FieldValidationException("nomeMarca", "Nome da marca é obrigatório.");
        }
    }

    @Override
    public void validateInsert(MarcaModel entity) {
        if (repository.existsByNomeMarcaIgnoreCase(entity.getNomeMarca())) {
            throw new RuleValidationException("marca-duplicada", "Já existe uma marca com esse nome.");
        }
    }
}