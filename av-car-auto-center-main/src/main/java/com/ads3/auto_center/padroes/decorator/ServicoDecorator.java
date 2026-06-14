package com.ads3.auto_center.padroes.decorator;

import java.math.BigDecimal;

public abstract class ServicoDecorator implements ServicoOrcavel {

    protected final ServicoOrcavel decorado;

    protected ServicoDecorator(ServicoOrcavel decorado) {
        if (decorado == null) throw new IllegalArgumentException("O componente decorado não pode ser nulo.");
        this.decorado = decorado;
    }

    @Override
    public String getDescricao() {
        return decorado.getDescricao();
    }

    @Override
    public BigDecimal getValor() {
        return decorado.getValor();
    }
}