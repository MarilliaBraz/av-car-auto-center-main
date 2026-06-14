package com.ads3.auto_center.business.marca;

import com.ads3.auto_center.core.domains.BaseModel;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "marcas")
@AttributeOverride(name = "id", column = @Column(name = "id_marca"))
public class MarcaModel extends BaseModel {

    @Column(name = "nome_marca", length = 50, nullable = false)
    private String nomeMarca;
}