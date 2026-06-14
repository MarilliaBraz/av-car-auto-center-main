package com.ads3.auto_center.business.veiculo;

import org.springframework.http.ResponseEntity;

public interface IVeiculoController {
    ResponseEntity<VeiculoDTO> buscarPorPlaca(String placa);
}