package com.ads3.auto_center.business.peca;

import com.ads3.auto_center.core.dtos.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class PecaDTO extends BaseDTO {
    private String nomePeca;
    private String codigoRef;
    private BigDecimal precoUnitario;
    private Integer estoqueAtual;
}