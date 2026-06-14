package com.ads3.auto_center.business.pessoaFisica;

import com.ads3.auto_center.core.controllers.GenericController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes/pessoas-fisicas")
public class PessoaFisicaController extends GenericController<PessoaFisicaModel, PessoaFisicaDTO, PessoaFisicaService, PessoaFisicaMapper> {

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<PessoaFisicaDTO> buscarPorCpf(@PathVariable String cpf) {
        PessoaFisicaModel entity = service.buscarPorCpf(cpf);
        return ResponseEntity.ok(mapper.toDto(entity));
    }
}