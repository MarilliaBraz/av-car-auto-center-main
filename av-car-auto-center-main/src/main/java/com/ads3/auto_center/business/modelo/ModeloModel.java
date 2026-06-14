package com.ads3.auto_center.business.modelo;

import com.ads3.auto_center.business.marca.MarcaModel;
import com.ads3.auto_center.core.domains.BaseModel;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "modelos")
@AttributeOverride(name = "id", column = @Column(name = "id_modelo"))
public class ModeloModel extends BaseModel {

    @Column(name = "nome_modelo", length = 80, nullable = false)
    private String nomeModelo;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private MarcaModel marca;
}