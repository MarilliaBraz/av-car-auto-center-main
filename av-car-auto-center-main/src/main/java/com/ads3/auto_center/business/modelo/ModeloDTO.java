package com.ads3.auto_center.business.modelo;

import com.ads3.auto_center.core.dtos.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModeloDTO extends BaseDTO {
    private String nomeModelo;
    private Long idMarca;
}