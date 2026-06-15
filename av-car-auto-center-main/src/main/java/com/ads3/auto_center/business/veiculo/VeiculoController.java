package com.ads3.auto_center.business.veiculo;

import com.ads3.auto_center.business.proprietarioHistorico.TransferirProprietarioRequest;
import com.ads3.auto_center.core.controllers.GenericController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController extends GenericController<VeiculoModel, VeiculoDTO, VeiculoService, VeiculoMapper> {

    @GetMapping("/placa/{placa}")
    public ResponseEntity<VeiculoDTO> buscarPorPlaca(@PathVariable String placa) {
        VeiculoModel veiculo = service.buscarPorPlaca(placa);
        return ResponseEntity.ok(mapper.toDto(veiculo));
    }

    @PostMapping("/{id}/transferir-proprietario")
    public ResponseEntity<Void> transferirProprietario(
            @PathVariable Long id,
            @RequestBody TransferirProprietarioRequest request) {
        service.transferirProprietario(id, request.getIdNovoCliente());
        return ResponseEntity.noContent().build();
    }
}