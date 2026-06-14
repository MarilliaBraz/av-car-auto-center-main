package com.ads3.auto_center.business.ordemDeServico;

import com.ads3.auto_center.core.exceptions.FieldValidationException;
import com.ads3.auto_center.core.validations.GenericValidation;
import org.springframework.stereotype.Component;

@Component
public class OrdemDeServicoValidacao extends GenericValidation<OrdemDeServicoModel, IOrdemDeServicoRepository> {

    @Override
    public void validateFields(OrdemDeServicoModel entity) {
        super.validateFields(entity);
        if (entity.getVeiculo() == null || entity.getVeiculo().getId() == null) {
            throw new FieldValidationException("idVeiculo", "A Ordem de Serviço deve estar vinculada a um veículo.");
        }
    }
}