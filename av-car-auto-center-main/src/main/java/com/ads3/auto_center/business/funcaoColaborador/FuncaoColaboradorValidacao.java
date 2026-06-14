package com.ads3.auto_center.business.funcaoColaborador;

import com.ads3.auto_center.core.exceptions.FieldValidationException;
import com.ads3.auto_center.core.validations.GenericValidation;
import org.springframework.stereotype.Component;

@Component
public class FuncaoColaboradorValidacao extends GenericValidation<FuncaoColaboradorModel, IFuncaoColaboradorRepository> {

    @Override
    public void validateFields(FuncaoColaboradorModel entity) {
        super.validateFields(entity);
        if (entity.getNomeFuncao() == null || entity.getNomeFuncao().isBlank()) {
            throw new FieldValidationException("nomeFuncao", "Nome da função é obrigatório.");
        }
    }
}