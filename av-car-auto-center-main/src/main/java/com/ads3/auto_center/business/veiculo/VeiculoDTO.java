package com.ads3.auto_center.business.veiculo;

import com.ads3.auto_center.core.dtos.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoDTO extends BaseDTO {
    private String placa;
    private int anoFabricacao;
    private String cor;
    private Long idModelo;
    private Long idCliente;
}