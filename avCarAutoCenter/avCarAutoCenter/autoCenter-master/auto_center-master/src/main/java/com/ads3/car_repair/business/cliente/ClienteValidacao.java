package com.ads3.car_repair.business.cliente;

import com.ads3.car_repair.core.exceptions.FieldValidationException;
import com.ads3.car_repair.core.validations.GenericValidation;
import org.springframework.stereotype.Component;

@Component
public class ClienteValidacao extends GenericValidation<ClienteModel, IClienteRepository> {
    @Override
    public void validateFields(ClienteModel entity) {
        super.validateFields(entity);

        if (entity.getNome() == null || entity.getNome().isBlank()) {
            throw new FieldValidationException("nome", "Nome é obrigatório.");
        }

        if (entity.getCpf() == null || entity.getCpf().isBlank()) {
            throw new FieldValidationException("cpf", "CPF é obrigatório.");
        }
    }

    @Override
    public void validateFieldsInsert(ClienteModel entity) {
        super.validateFieldsInsert(entity);

        if (repository.existsByCpf(entity.getCpf())) {
            throw new FieldValidationException("cpf", "CPF já cadastrado.");
        }

        if (repository.existsByTelefone(entity.getTelefone())) {
            throw new FieldValidationException("telefone", "Telefone já cadastrado.");
        }
    }

    @Override
    public void validateFieldsUpdate(ClienteModel entity) {
        super.validateFieldsUpdate(entity);

        ClienteModel clienteBanco = repository.findByCpf(entity.getCpf());

        if (clienteBanco != null &&
                !clienteBanco.getId().equals(entity.getId())) {

            throw new FieldValidationException("cpf", "CPF já cadastrado.");
        }
    }
}
