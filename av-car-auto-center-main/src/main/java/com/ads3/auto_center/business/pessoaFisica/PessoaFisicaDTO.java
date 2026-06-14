package com.ads3.auto_center.business.pessoaFisica;

import com.ads3.auto_center.core.dtos.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class PessoaFisicaDTO extends BaseDTO {
    private String telefone;
    private String email;
    private String endereco;
    private LocalDate dataCadastro;
    private String cpf;
    private String nome;
    private LocalDate dataNascimento;
}