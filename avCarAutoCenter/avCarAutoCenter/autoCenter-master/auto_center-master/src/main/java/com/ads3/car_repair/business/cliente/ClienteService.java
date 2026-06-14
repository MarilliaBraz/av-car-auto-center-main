package com.ads3.car_repair.business.cliente;

import com.ads3.car_repair.core.exceptions.RuleValidationException;
import com.ads3.car_repair.core.services.GenericService;
import org.springframework.stereotype.Service;

@Service
public class ClienteService extends GenericService<ClienteModel, IClienteRepository, ClienteValidacao> {
    public ClienteModel buscarPorCpf(String cpf) {
        ClienteModel cliente = repository.findByCpf(cpf);

        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado.");
        }

        return cliente;
    }
    @Override
    protected void beforeDelete(ClienteModel entity) {

        if (!entity.getVeiculos().isEmpty()) {
            throw new RuleValidationException(
                    "cliente-com-veiculos",
                    "Cliente possui veículos vinculados."
            );
        }

    }
}
