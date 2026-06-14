package com.ads3.auto_center.business.servicoExterno;

import com.ads3.auto_center.core.dtos.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ServicoExternoDTO extends BaseDTO {
    private String nomeServico;
    private String descricao;
    private BigDecimal precoBase;
}