package com.ads3.car_repair.business.servico;

import com.ads3.car_repair.core.dtos.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ServicoDTO extends BaseDTO {

    // Referência à OS pelo ID (evita ciclo de serialização)
    private UUID osId;

    private String descricao;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraTermino;
    private String mecanico;
    private BigDecimal valor;
}
