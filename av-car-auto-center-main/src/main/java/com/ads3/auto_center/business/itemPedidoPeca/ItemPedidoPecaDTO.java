package com.ads3.auto_center.business.itemPedidoPeca;

import com.ads3.auto_center.core.dtos.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ItemPedidoPecaDTO extends BaseDTO {
    private Integer quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal desconto;
    private Long idOs;
    private Long idPeca;
}