package com.ads3.car_repair.business.veiculo;

import com.ads3.car_repair.core.exceptions.RuleValidationException;
import com.ads3.car_repair.core.validations.GenericValidation;
import org.springframework.stereotype.Component;

@Component
public class VeiculoValidation extends GenericValidation<VeiculoModel, IVeiculoRepository> implements IVeiculoValidation{

    @Override
    public void validateInsert(VeiculoModel entity) {
        if (repository.existsByPlaca(entity.getPlaca())) {
            throw new RuleValidationException("placa-duplicada", "Já existe um veículo cadastrado com a placa informada.");
        }
    }
}
