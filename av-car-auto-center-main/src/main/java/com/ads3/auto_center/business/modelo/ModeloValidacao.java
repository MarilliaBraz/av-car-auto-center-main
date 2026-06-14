package com.ads3.auto_center.business.modelo;

import com.ads3.auto_center.core.exceptions.FieldValidationException;
import com.ads3.auto_center.core.validations.GenericValidation;
import org.springframework.stereotype.Component;

@Component
public class ModeloValidacao extends GenericValidation<ModeloModel, IModeloRepository> {

    @Override
    public void validateFields(ModeloModel entity) {
        super.validateFields(entity);
        if (entity.getNomeModelo() == null || entity.getNomeModelo().isBlank()) {
            throw new FieldValidationException("nomeModelo", "Nome do modelo é obrigatório.");
        }
        if (entity.getMarca() == null || entity.getMarca().getId() == null) {
            throw new FieldValidationException("idMarca", "A marca é obrigatória.");
        }
    }
}