package com.ads3.auto_center.business.pessoaJuridica;

import com.ads3.auto_center.business.cliente.ClienteModel;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "pessoas_juridicas")
@PrimaryKeyJoinColumn(name = "id_pessoa_juridica")
public class PessoaJuridicaModel extends ClienteModel {

    @Column(name = "cnpj", length = 18, nullable = false, unique = true)
    private String cnpj;

    @Column(name = "razao_social", length = 150, nullable = false)
    private String razaoSocial;

    @Column(name = "nome_fantasia", length = 100)
    private String nomeFantasia;
}