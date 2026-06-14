package com.ads3.auto_center.business.veiculo;

import com.ads3.auto_center.core.exceptions.FieldValidationException;
import com.ads3.auto_center.core.exceptions.RuleValidationException;
import com.ads3.auto_center.core.validations.GenericValidation;
import org.springframework.stereotype.Component;

@Component
public class VeiculoValidation extends GenericValidation<VeiculoModel, IVeiculoRepository> {

    @Override
    public void validateFields(VeiculoModel entity) {
        super.validateFields(entity);
        if (entity.getPlaca() == null || entity.getPlaca().isBlank()) {
            throw new FieldValidationException("placa", "Placa é obrigatória.");
        }
        if (entity.getAnoFabricacao() < 1900 || entity.getAnoFabricacao() > 2099) {
            throw new FieldValidationException("anoFabricacao", "Ano de fabricação deve estar entre 1900 e 2099.");
        }
    }

    @Override
    public void validateInsert(VeiculoModel entity) {
        if (repository.existsByPlaca(entity.getPlaca())) {
            throw new RuleValidationException("placa-duplicada", "Já existe um veículo cadastrado com a placa informada.");
        }
    }
}