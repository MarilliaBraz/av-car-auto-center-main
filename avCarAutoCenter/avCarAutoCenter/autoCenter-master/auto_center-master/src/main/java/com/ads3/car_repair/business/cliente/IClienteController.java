package com.ads3.car_repair.business.cliente;

import org.springframework.http.ResponseEntity;

public interface IClienteController {
    ResponseEntity<ClienteDTO> buscarPorCpf(String cpf);
}
