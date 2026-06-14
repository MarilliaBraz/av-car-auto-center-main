package com.ads3.car_repair.business.ordemDeServico;

import com.ads3.car_repair.core.dtos.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class OrdemDeServicoDTO extends BaseDTO {


    private UUID clienteId;
    private UUID veiculoId;

    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;
    private String problema;
    private String observacao;
}
