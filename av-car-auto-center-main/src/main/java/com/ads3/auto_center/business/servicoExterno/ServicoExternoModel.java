package com.ads3.auto_center.business.servicoExterno;

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
@Table(name = "servicos_externos")
@AttributeOverride(name = "id", column = @Column(name = "id_srv_externo"))
public class ServicoExternoModel extends BaseModel {

    @Column(name = "nome_servico", length = 100, nullable = false)
    private String nomeServico;

    @Column(name = "descricao", length = 300)
    private String descricao;

    @Column(name = "preco_base", precision = 10, scale = 2)
    private BigDecimal precoBase;
}