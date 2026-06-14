package com.ads3.car_repair.business.cliente;

import com.ads3.car_repair.core.repositories.IGenericRepository;

public interface IClienteRepository extends IGenericRepository<ClienteModel> {
    boolean existsByTelefone(String telefone);

    boolean existsByCpf(String cpf);

    ClienteModel findByCpf(String cpf);
}
