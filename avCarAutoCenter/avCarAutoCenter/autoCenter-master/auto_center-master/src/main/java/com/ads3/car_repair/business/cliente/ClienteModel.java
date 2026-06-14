package com.ads3.car_repair.business.cliente;


import com.ads3.car_repair.business.veiculo.VeiculoModel;
import com.ads3.car_repair.core.domains.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "clientes")
public class ClienteModel extends BaseModel {

    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Column(name = "telefone", length = 20,unique = true, nullable = false)
    private String telefone;

    @Column(name = "cpf", length = 11,unique = true, nullable = false)
    private String cpf;

    @Column(name = "endereco", length = 120, nullable = false)
    private String endereco;

    @Column(name = "bairro", length = 50, nullable = false)
    private String Bairro;

    @Column(name = "cidade", length = 20, nullable = false)
    private String cidade;

    @Column(name = "estado", length = 20, nullable = false)
    private String estado;

    @Column(name = "cep", length = 20, nullable = false)
    private String cep;

    @OneToMany(mappedBy = "cliente")
    private List<VeiculoModel> veiculos;
}
