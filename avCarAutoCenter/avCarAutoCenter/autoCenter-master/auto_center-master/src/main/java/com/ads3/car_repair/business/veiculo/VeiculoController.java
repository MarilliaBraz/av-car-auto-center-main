package com.ads3.car_repair.business.veiculo;

import com.ads3.car_repair.business.cliente.IClienteController;
import com.ads3.car_repair.core.controllers.GenericController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController extends GenericController<VeiculoModel, VeiculoDTO, VeiculoService, VeiculoMapper> implements IVeiculoController {
    @Override
    @GetMapping("/placa/{placa}")
    public ResponseEntity<VeiculoDTO> buscarPorPlaca(
            @PathVariable String placa) {

        VeiculoModel veiculo = service.buscarPorPlaca(placa);

        return ResponseEntity.ok(mapper.toDto(veiculo));
    }
}
