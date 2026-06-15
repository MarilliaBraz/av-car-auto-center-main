package com.ads3.auto_center.business.itemServicoInterno;

import com.ads3.auto_center.business.servicoInterno.ServicoInternoModel;
import com.ads3.auto_center.business.servicoInterno.ServicoInternoService;
import com.ads3.auto_center.core.controllers.GenericController;
import com.ads3.auto_center.padroes.decorator.ServicoBase;
import com.ads3.auto_center.padroes.decorator.ServicoComGarantia;
import com.ads3.auto_center.padroes.decorator.ServicoComUrgencia;
import com.ads3.auto_center.padroes.decorator.ServicoOrcavel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/itens-servico-interno")
public class ItemServicoInternoController extends GenericController<ItemServicoInternoModel, ItemServicoInternoDTO, ItemServicoInternoService, ItemServicoInternoMapper> {

    @Autowired
    private ServicoInternoService servicoInternoService;

    @GetMapping("/por-os/{idOs}")
    public ResponseEntity<List<ItemServicoInternoDTO>> listarPorOs(@PathVariable Long idOs) {
        List<ItemServicoInternoDTO> itens = service.listarPorOs(idOs)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(itens);
    }

    @PostMapping("/orcamento")
    public ResponseEntity<OrcamentoServicoResponse> calcularOrcamento(@RequestBody OrcamentoServicoRequest request) {
        ServicoInternoModel servicoModel = servicoInternoService.findByIdActive(request.getIdSrvInterno());

        ServicoOrcavel servico = new ServicoBase(servicoModel);

        if (request.isComUrgencia()) {
            servico = new ServicoComUrgencia(servico);
        }

        if (request.isComGarantia() && request.getMesesGarantia() != null && request.getMesesGarantia() > 0) {
            servico = new ServicoComGarantia(servico, request.getMesesGarantia());
        }

        return ResponseEntity.ok(new OrcamentoServicoResponse(servico.getDescricao(), servico.getValor()));
    }
}