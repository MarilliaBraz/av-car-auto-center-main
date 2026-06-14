package com.ads3.car_repair.business.veiculo;

import com.ads3.car_repair.core.dtos.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoDTO extends BaseDTO {

    private String marca;
    private String modelo;
    private int anoFabric;
    private int anoModelo;
    private String placa;
    private String cor;
}
