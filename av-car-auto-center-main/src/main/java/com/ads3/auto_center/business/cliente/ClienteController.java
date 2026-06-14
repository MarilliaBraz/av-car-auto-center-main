package com.ads3.auto_center.business.cliente;

import com.ads3.auto_center.business.pessoaFisica.PessoaFisicaDTO;
import com.ads3.auto_center.business.pessoaFisica.PessoaFisicaMapper;
import com.ads3.auto_center.business.pessoaFisica.PessoaFisicaModel;
import com.ads3.auto_center.business.pessoaFisica.PessoaFisicaService;
import com.ads3.auto_center.business.pessoaJuridica.PessoaJuridicaDTO;
import com.ads3.auto_center.business.pessoaJuridica.PessoaJuridicaMapper;
import com.ads3.auto_center.business.pessoaJuridica.PessoaJuridicaModel;
import com.ads3.auto_center.business.pessoaJuridica.PessoaJuridicaService;
import com.ads3.auto_center.business.veiculo.VeiculoModel;
import com.ads3.auto_center.business.veiculo.VeiculoService;
import com.ads3.auto_center.core.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private PessoaFisicaService pessoaFisicaService;
    @Autowired
    private PessoaFisicaMapper pessoaFisicaMapper;
    @Autowired
    private PessoaJuridicaService pessoaJuridicaService;
    @Autowired
    private PessoaJuridicaMapper pessoaJuridicaMapper;
    @Autowired
    private VeiculoService veiculoService;

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<PessoaFisicaDTO> buscarPorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(pessoaFisicaMapper.toDto(pessoaFisicaService.buscarPorCpf(cpf)));
    }

    @GetMapping("/cnpj")
    public ResponseEntity<PessoaJuridicaDTO> buscarPorCnpj(@RequestParam String cnpj) {
        return ResponseEntity.ok(pessoaJuridicaMapper.toDto(pessoaJuridicaService.buscarPorCnpj(cnpj)));
    }

    @GetMapping("/placa/{placa}")
    public ResponseEntity<?> buscarPorPlaca(@PathVariable String placa) {
        VeiculoModel veiculo = veiculoService.buscarPorPlaca(placa);
        ClienteModel cliente = veiculo.getCliente();
        if (cliente == null) {
            throw new BusinessException("Nenhum cliente vinculado ao veículo com a placa informada.", HttpStatus.NOT_FOUND);
        }
        String nome;
        if (cliente instanceof PessoaFisicaModel pf) {
            nome = pf.getNome();
        } else if (cliente instanceof PessoaJuridicaModel pj) {
            nome = pj.getRazaoSocial();
        } else {
            nome = null;
        }
        return ResponseEntity.ok(new ClienteResumoDTO(nome, cliente.getTelefone(), cliente.getEmail()));
    }
}