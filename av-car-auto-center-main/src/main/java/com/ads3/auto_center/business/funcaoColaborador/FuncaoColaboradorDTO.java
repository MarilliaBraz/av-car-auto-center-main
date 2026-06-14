package com.ads3.auto_center.business.funcaoColaborador;

import com.ads3.auto_center.core.dtos.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncaoColaboradorDTO extends BaseDTO {
    private String nomeFuncao;
    private String descricao;
}