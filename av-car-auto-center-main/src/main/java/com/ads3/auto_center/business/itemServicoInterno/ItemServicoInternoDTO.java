package com.ads3.auto_center.business.itemServicoInterno;

import com.ads3.auto_center.core.dtos.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ItemServicoInternoDTO extends BaseDTO {
    private Integer quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal desconto;
    private Long idOs;
    private Long idSrvInterno;
    private String nomeServico;
}