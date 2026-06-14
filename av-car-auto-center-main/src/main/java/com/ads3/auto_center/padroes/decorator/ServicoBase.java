package com.ads3.auto_center.padroes.decorator;

import com.ads3.auto_center.business.servicoInterno.ServicoInternoModel;

import java.math.BigDecimal;

public class ServicoBase implements ServicoOrcavel {

    private final ServicoInternoModel servico;

    public ServicoBase(ServicoInternoModel servico) {
        if (servico == null) throw new IllegalArgumentException("Serviço não pode ser nulo.");
        this.servico = servico;
    }

    @Override
    public String getDescricao() {
        return servico.getNomeServico();
    }

    @Override
    public BigDecimal getValor() {
        return servico.getPrecoBase();
    }
}