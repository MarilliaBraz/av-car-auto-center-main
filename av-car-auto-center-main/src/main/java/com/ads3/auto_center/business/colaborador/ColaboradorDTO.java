package com.ads3.auto_center.business.colaborador;

import com.ads3.auto_center.core.dtos.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ColaboradorDTO extends BaseDTO {
    private String telefone;
    private String email;
    private String endereco;
    private String nome;
    private String cpf;
    private LocalDate dataAdmissao;
    private BigDecimal salario;
    private List<Long> idFuncoes;
}