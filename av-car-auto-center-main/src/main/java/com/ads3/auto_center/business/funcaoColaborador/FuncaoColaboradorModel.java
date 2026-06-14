package com.ads3.auto_center.business.funcaoColaborador;

import com.ads3.auto_center.core.domains.BaseModel;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "funcoes_colaborador")
@AttributeOverride(name = "id", column = @Column(name = "id_funcao"))
public class FuncaoColaboradorModel extends BaseModel {

    @Column(name = "nome_funcao", length = 80, nullable = false)
    private String nomeFuncao;

    @Column(name = "descricao", length = 200)
    private String descricao;
}