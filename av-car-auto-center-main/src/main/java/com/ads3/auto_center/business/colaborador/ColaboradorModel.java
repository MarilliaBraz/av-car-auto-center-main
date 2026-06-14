package com.ads3.auto_center.business.colaborador;

import com.ads3.auto_center.business.funcaoColaborador.FuncaoColaboradorModel;
import com.ads3.auto_center.business.pessoa.PessoaModel;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "colaboradores")
@PrimaryKeyJoinColumn(name = "id_colaborador")
public class ColaboradorModel extends PessoaModel {

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "cpf", length = 14, nullable = false, unique = true)
    private String cpf;

    @Column(name = "data_admissao", nullable = false)
    private LocalDate dataAdmissao;

    @Column(name = "salario", precision = 10, scale = 2)
    private BigDecimal salario;

    @ManyToMany
    @JoinTable(
            name = "colaborador_funcao",
            joinColumns = @JoinColumn(name = "id_colaborador"),
            inverseJoinColumns = @JoinColumn(name = "id_funcao")
    )
    private List<FuncaoColaboradorModel> funcoes;
}