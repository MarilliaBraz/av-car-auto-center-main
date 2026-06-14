package com.ads3.auto_center.business.cliente;

import com.ads3.auto_center.business.pessoa.PessoaModel;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "clientes")
@PrimaryKeyJoinColumn(name = "id_cliente")
public class ClienteModel extends PessoaModel {

    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro;

    @PrePersist
    public void preClientePersist() {
        if (this.dataCadastro == null) {
            this.dataCadastro = LocalDate.now();
        }
    }
}