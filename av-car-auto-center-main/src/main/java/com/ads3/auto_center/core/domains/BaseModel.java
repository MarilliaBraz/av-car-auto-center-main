package com.ads3.auto_center.core.domains;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data_hora_criacao", updatable = false)
    private LocalDateTime dataHoraCriacao;

    @Column(name = "ativo")
    private boolean ativo;

    @PrePersist
    public void prePersist() {
        this.dataHoraCriacao = LocalDateTime.now();
        this.ativo = true;
    }
}