package com.ads3.auto_center.business.veiculo;

import com.ads3.auto_center.business.cliente.ClienteModel;
import com.ads3.auto_center.business.modelo.ModeloModel;
import com.ads3.auto_center.core.domains.BaseModel;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "veiculos")
@AttributeOverride(name = "id", column = @Column(name = "id_veiculo"))
public class VeiculoModel extends BaseModel {

    @Column(name = "placa", length = 8, nullable = false, unique = true)
    private String placa;

    @Column(name = "ano_fabricacao", nullable = false)
    private int anoFabricacao;

    @Column(name = "cor", length = 30)
    private String cor;

    @ManyToOne
    @JoinColumn(name = "id_modelo")
    private ModeloModel modelo;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClienteModel cliente;
}