package com.ads3.auto_center.business.ordemDeServico;

import com.ads3.auto_center.business.veiculo.VeiculoModel;
import com.ads3.auto_center.core.domains.BaseModel;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ordens_de_servico")
@AttributeOverride(name = "id", column = @Column(name = "id_os"))
public class OrdemDeServicoModel extends BaseModel {

    @Column(name = "numero_os", length = 15, unique = true)
    private String numero;

    @Column(name = "sequencial")
    private Integer sequencial;

    @Column(name = "data_abertura", nullable = false)
    private LocalDateTime dataAbertura;

    @Column(name = "data_fechamento")
    private LocalDateTime dataFechamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private StatusOS status;

    @Column(name = "valor_total", precision = 12, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "observacoes", length = 500)
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "id_veiculo")
    private VeiculoModel veiculo;

    @PrePersist
    public void preOsPersist() {
        if (dataAbertura == null) dataAbertura = LocalDateTime.now(ZoneOffset.UTC);
        if (status == null) status = StatusOS.ORCAMENTO;
    }
}