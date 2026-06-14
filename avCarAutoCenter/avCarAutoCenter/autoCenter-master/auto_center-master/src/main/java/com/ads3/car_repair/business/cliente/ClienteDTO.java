package com.ads3.car_repair.business.cliente;

import com.ads3.car_repair.core.dtos.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO extends BaseDTO {
    private String nome;
    private String telefone;
    private String cpf;
    private String endereco;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
}
