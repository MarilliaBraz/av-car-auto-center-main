package com.ads3.auto_center.business.fornecedor;

import com.ads3.auto_center.core.domains.BaseModel;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "fornecedores")
@AttributeOverride(name = "id", column = @Column(name = "id_fornecedor"))
public class FornecedorModel extends BaseModel {

    @Column(name = "nome_fornecedor", length = 150, nullable = false)
    private String nomeFornecedor;

    @Column(name = "cnpj", length = 18, unique = true)
    private String cnpj;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "endereco", length = 200)
    private String endereco;
}