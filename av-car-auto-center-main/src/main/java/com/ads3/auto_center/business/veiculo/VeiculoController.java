package com.ads3.auto_center.business.veiculo;

import com.ads3.auto_center.business.proprietarioHistorico.TransferirProprietarioRequest;
import com.ads3.auto_center.core.controllers.GenericController;
import com.ads3.auto_center.padroes.adapter.ConsultaVeiculoPort;
import com.ads3.auto_center.padroes.adapter.DadosVeiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController extends GenericController<VeiculoModel, VeiculoDTO, VeiculoService, VeiculoMapper> {

    @Autowired
    private ConsultaVeiculoPort consultaVeiculoPort;

    @GetMapping("/placa/{placa}")
    public ResponseEntity<VeiculoDTO> buscarPorPlaca(@PathVariable String placa) {
        VeiculoModel veiculo = service.buscarPorPlaca(placa);
        return ResponseEntity.ok(mapper.toDto(veiculo));
    }

    @GetMapping("/consultar-placa/{placa}")
    public ResponseEntity<DadosVeiculo> consultarPlaca(@PathVariable String placa) {
        DadosVeiculo dados = consultaVeiculoPort.consultarPorPlaca(placa);
        return ResponseEntity.ok(dados);
    }

    @PostMapping("/{id}/transferir-proprietario")
    public ResponseEntity<Void> transferirProprietario(
            @PathVariable Long id,
            @RequestBody TransferirProprietarioRequest request) {
        service.transferirProprietario(id, request.getIdNovoCliente());
        return ResponseEntity.noContent().build();
    }
}