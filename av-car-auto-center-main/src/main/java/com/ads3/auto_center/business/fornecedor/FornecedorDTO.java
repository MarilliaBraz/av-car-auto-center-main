package com.ads3.auto_center.business.fornecedor;

import com.ads3.auto_center.core.dtos.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FornecedorDTO extends BaseDTO {
    private String nomeFornecedor;
    private String cnpj;
    private String telefone;
    private String email;
    private String endereco;
}