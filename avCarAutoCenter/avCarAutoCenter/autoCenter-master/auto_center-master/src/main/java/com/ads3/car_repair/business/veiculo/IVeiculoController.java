package com.ads3.car_repair.business.veiculo;

import org.springframework.http.ResponseEntity;

public interface IVeiculoController {
    ResponseEntity<VeiculoDTO> buscarPorPlaca(String placa);
}
