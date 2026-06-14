package com.ads3.auto_center.padroes.decorator;

import java.math.BigDecimal;

public class ServicoComUrgencia extends ServicoDecorator {

    private static final BigDecimal TAXA_URGENCIA = new BigDecimal("50.00");

    public ServicoComUrgencia(ServicoOrcavel decorado) {
        super(decorado);
    }

    @Override
    public String getDescricao() {
        return decorado.getDescricao() + " + Atendimento Urgente";
    }

    @Override
    public BigDecimal getValor() {
        return decorado.getValor().add(TAXA_URGENCIA);
    }
}