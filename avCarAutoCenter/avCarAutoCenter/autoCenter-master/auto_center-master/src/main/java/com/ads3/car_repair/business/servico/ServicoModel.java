package com.ads3.car_repair.business.servico;

import com.ads3.car_repair.business.ordemDeServico.OrdemDeServicoModel;
import com.ads3.car_repair.core.domains.BaseModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "servicos")
public class ServicoModel extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "os_id")
    private OrdemDeServicoModel os;


    @Column(name = "descricao", length = 120, nullable = false)
    private String descricao;

    @Column(name = "dataHoraInicio", nullable = false)
    private LocalDateTime dataHoraInicio;

    @Column(name = "dataHoraTermino")
    private LocalDateTime dataHoraTermino;

    @Column(name = "mecanicos", length = 40, nullable = false)
    private String mecanico;

    @Column(name = "valor", length = 20, nullable = false)
    private BigDecimal valor;



}
