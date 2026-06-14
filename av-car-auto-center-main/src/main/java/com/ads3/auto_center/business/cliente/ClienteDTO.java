package com.ads3.auto_center.business.cliente;

import com.ads3.auto_center.core.dtos.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class ClienteDTO extends BaseDTO {
    private String telefone;
    private String email;
    private String endereco;
    private LocalDate dataCadastro;
}