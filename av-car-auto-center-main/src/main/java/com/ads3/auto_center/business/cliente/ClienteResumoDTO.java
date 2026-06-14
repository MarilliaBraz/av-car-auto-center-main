package com.ads3.auto_center.business.cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClienteResumoDTO {
    private String nome;
    private String telefone;
    private String email;
}