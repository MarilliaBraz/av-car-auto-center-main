package com.ads3.auto_center.business.itemServicoExterno;

import com.ads3.auto_center.business.ordemDeServico.OrdemDeServicoModel;
import com.ads3.auto_center.business.servicoExterno.ServicoExternoModel;
import com.ads3.auto_center.core.domains.BaseModel;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "itens_servico_externo")
@AttributeOverride(name = "id", column = @Column(name = "id_item_srv_ext"))
public class ItemServicoExternoModel extends BaseModel {

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "valor_unitario", precision = 10, scale = 2, nullable = false)
    private BigDecimal valorUnitario;

    @Column(name = "desconto", precision = 5, scale = 2)
    private BigDecimal desconto;

    @ManyToOne
    @JoinColumn(name = "id_os")
    private OrdemDeServicoModel ordemDeServico;

    @ManyToOne
    @JoinColumn(name = "id_srv_externo")
    private ServicoExternoModel servicoExterno;
}