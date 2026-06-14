package com.ads3.car_repair.business.ordemDeServico;

import com.ads3.car_repair.core.exceptions.FieldValidationException;
import com.ads3.car_repair.core.validations.GenericValidation;
import org.springframework.stereotype.Component;

@Component
public class OrdemDeServicoValidacao extends GenericValidation<OrdemDeServicoModel, IOrdemDeServicoRepository> {

    @Override
    public void validateFields(OrdemDeServicoModel entity) {
        super.validateFields(entity);

        if (entity.getClienteModel() == null) {
            throw new FieldValidationException("clienteId", "A Ordem de Serviço deve estar vinculada a um cliente.");
        }
        if (entity.getVeiculoModel() == null) {
            throw new FieldValidationException("veiculoId", "A Ordem de Serviço deve estar vinculada a um veículo.");
        }
        if (entity.getDataEntrada() == null) {
            throw new FieldValidationException("dataEntrada", "A data de entrada é obrigatória.");
        }
        if (entity.getProblema() == null || entity.getProblema().isBlank()) {
            throw new FieldValidationException("problema", "A descrição do problema é obrigatória.");
        }
    }
}
