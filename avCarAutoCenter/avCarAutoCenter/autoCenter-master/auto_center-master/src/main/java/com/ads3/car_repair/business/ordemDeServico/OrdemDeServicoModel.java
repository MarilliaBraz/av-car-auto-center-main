package com.ads3.car_repair.business.ordemDeServico;

import com.ads3.car_repair.business.cliente.ClienteModel;
import com.ads3.car_repair.business.servico.ServicoModel;
import com.ads3.car_repair.business.veiculo.VeiculoModel;
import com.ads3.car_repair.core.domains.BaseModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "Ordens_de_Servico")
@EqualsAndHashCode(callSuper = true)
public class OrdemDeServicoModel extends BaseModel {

    @Column(name = "status", length = 20, nullable = false)
    private String status;

    @Column(name = "data_entrada", length = 8, nullable = false)
    private LocalDateTime dataEntrada;

    @Column(name = "data_saida", length = 8)
    private LocalDateTime dataSaida;

    @Column(name = "problema", length = 120, nullable = false)
    private String problema;

    @Column(name = "observacao", length = 100)
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteModel clienteModel;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private VeiculoModel veiculoModel;

    @ManyToMany
    @JoinTable(
            name = "ordem_servico_servico",
            joinColumns = @JoinColumn(name = "ordem_servico_id"),
            inverseJoinColumns = @JoinColumn(name = "servico_id")
    )
    private List<ServicoModel> servicos;

}
