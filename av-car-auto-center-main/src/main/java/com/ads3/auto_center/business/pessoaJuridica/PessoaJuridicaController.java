package com.ads3.auto_center.business.pessoaJuridica;

import com.ads3.auto_center.core.controllers.GenericController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes/pessoas-juridicas")
public class PessoaJuridicaController extends GenericController<PessoaJuridicaModel, PessoaJuridicaDTO, PessoaJuridicaService, PessoaJuridicaMapper> {

    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<PessoaJuridicaDTO> buscarPorCnpj(@PathVariable String cnpj) {
        PessoaJuridicaModel entity = service.buscarPorCnpj(cnpj);
        return ResponseEntity.ok(mapper.toDto(entity));
    }
}