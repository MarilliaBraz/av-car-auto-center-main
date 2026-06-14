package com.ads3.auto_center.business.itemPedidoPeca;

import com.ads3.auto_center.business.ordemDeServico.OrdemDeServicoModel;
import com.ads3.auto_center.business.peca.PecaModel;
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
@Table(name = "itens_pedido_peca")
@AttributeOverride(name = "id", column = @Column(name = "id_item_peca"))
public class ItemPedidoPecaModel extends BaseModel {

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
    @JoinColumn(name = "id_peca")
    private PecaModel peca;
}