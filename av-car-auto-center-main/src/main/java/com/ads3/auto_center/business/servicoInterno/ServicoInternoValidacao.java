package com.ads3.auto_center.business.servicoInterno;

import com.ads3.auto_center.core.exceptions.FieldValidationException;
import com.ads3.auto_center.core.validations.GenericValidation;
import org.springframework.stereotype.Component;

@Component
public class ServicoInternoValidacao extends GenericValidation<ServicoInternoModel, IServicoInternoRepository> {

    @Override
    public void validateFields(ServicoInternoModel entity) {
        super.validateFields(entity);
        if (entity.getNomeServico() == null || entity.getNomeServico().isBlank()) {
            throw new FieldValidationException("nomeServico", "Nome do serviço é obrigatório.");
        }
        if (entity.getPrecoBase() == null || entity.getPrecoBase().signum() <= 0) {
            throw new FieldValidationException("precoBase", "Preço base deve ser maior que zero.");
        }
    }
}