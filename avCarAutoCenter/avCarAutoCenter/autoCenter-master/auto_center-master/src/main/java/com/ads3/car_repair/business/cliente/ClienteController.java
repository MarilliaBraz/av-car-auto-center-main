package com.ads3.car_repair.business.cliente;

import com.ads3.car_repair.core.controllers.GenericController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController extends GenericController<ClienteModel, ClienteDTO, ClienteService, ClienteMapper> implements IClienteController{
    @Override
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClienteDTO> buscarPorCpf(String cpf) {
        ClienteModel cliente = service.buscarPorCpf(cpf);

        ClienteDTO dto = mapper.toDto(cliente);

        return ResponseEntity.ok(dto);
    }
}
