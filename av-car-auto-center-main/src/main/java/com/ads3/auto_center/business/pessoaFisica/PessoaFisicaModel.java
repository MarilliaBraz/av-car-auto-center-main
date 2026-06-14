
package com.ads3.auto_center.business.pessoaFisica;

import com.ads3.auto_center.business.cliente.ClienteModel;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "pessoas_fisicas")
@PrimaryKeyJoinColumn(name = "id_pessoa_fisica")
public class PessoaFisicaModel extends ClienteModel {

    @Column(name = "cpf", length = 14, nullable = false, unique = true)
    private String cpf;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
}