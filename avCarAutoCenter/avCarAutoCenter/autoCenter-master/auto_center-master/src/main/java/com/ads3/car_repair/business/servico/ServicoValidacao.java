package com.ads3.car_repair.business.servico;

import com.ads3.car_repair.core.exceptions.FieldValidationException;
import com.ads3.car_repair.core.validations.GenericValidation;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ServicoValidacao extends GenericValidation<ServicoModel, IServicoRepository> {

    @Override
    public void validateFields(ServicoModel entity) {
        super.validateFields(entity);

        if (entity.getDescricao() == null || entity.getDescricao().isBlank()) {
            throw new FieldValidationException("descricao", "A descrição do serviço é obrigatória.");
        }
        if (entity.getDataHoraInicio() == null) {
            throw new FieldValidationException("dataHoraInicio", "A data/hora de início é obrigatória.");
        }
        if (entity.getMecanico() == null || entity.getMecanico().isBlank()) {
            throw new FieldValidationException("mecanico", "O nome do mecânico é obrigatório.");
        }
        if (entity.getValor() == null || entity.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new FieldValidationException("valor", "O valor do serviço deve ser maior que zero.");
        }
        if (entity.getOs() == null) {
            throw new FieldValidationException("osId", "O serviço deve estar vinculado a uma Ordem de Serviço.");
        }
    }
}
