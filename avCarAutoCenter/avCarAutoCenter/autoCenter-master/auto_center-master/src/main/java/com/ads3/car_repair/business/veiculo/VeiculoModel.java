package com.ads3.car_repair.business.veiculo;


import com.ads3.car_repair.business.cliente.ClienteModel;
import com.ads3.car_repair.business.ordemDeServico.OrdemDeServicoModel;
import com.ads3.car_repair.core.domains.BaseModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "veiculos")
public class VeiculoModel extends BaseModel {

    @Column(name = "marca", length = 20, nullable = false)
    private String marca;

    @Column(name = "modelo", length = 20, nullable = false)
    private String modelo;

    @Column(name = "anoFabric", nullable = false)
    private int anoFabric;

    @Column(name = "anoModelo", nullable = false)
    private int anoModelo;

    @Column(name = "placa",length = 7, unique = true, nullable = false)
    private String placa;

    @Column(name = "cor",length = 20, nullable = false)
    private String cor;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteModel cliente;

    @OneToMany(mappedBy = "veiculoModel")
    private List<OrdemDeServicoModel> ordensServico;
}
