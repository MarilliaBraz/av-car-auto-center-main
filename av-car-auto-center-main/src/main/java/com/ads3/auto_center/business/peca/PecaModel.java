package com.ads3.auto_center.business.peca;

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
@Table(name = "pecas")
@AttributeOverride(name = "id", column = @Column(name = "id_peca"))
public class PecaModel extends BaseModel {

    @Column(name = "nome_peca", length = 100, nullable = false)
    private String nomePeca;

    @Column(name = "codigo_ref", length = 30, unique = true)
    private String codigoRef;

    @Column(name = "preco_unitario", precision = 10, scale = 2, nullable = false)
    private BigDecimal precoUnitario;

    @Column(name = "estoque_atual", nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer estoqueAtual = 0;
}