package com.ads3.auto_center.business.ordemDeServico;

import com.ads3.auto_center.core.dtos.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrdemDeServicoDTO extends BaseDTO {
    private String numero;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataFechamento;
    private StatusOS status;
    private BigDecimal valorTotal;
    private String observacoes;
    private Long idVeiculo;
}