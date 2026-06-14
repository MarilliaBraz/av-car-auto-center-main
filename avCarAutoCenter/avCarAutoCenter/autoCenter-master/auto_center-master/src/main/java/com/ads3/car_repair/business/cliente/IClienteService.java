package com.ads3.car_repair.business.cliente;


import com.ads3.car_repair.core.services.IGenericService;

public interface IClienteService extends IGenericService<ClienteModel, IClienteRepository, IClienteValidation> {
    ClienteModel buscarPorCpf(String cpf);
}
