package com.ads3.auto_center.padroes.decorator;

import java.math.BigDecimal;

public class ServicoComGarantia extends ServicoDecorator {

    private static final BigDecimal TAXA_GARANTIA = new BigDecimal("30.00");
    private final int mesesGarantia;

    public ServicoComGarantia(ServicoOrcavel decorado, int mesesGarantia) {
        super(decorado);
        this.mesesGarantia = mesesGarantia;
    }

    @Override
    public String getDescricao() {
        return decorado.getDescricao() + " + Garantia " + mesesGarantia + " meses";
    }

    @Override
    public BigDecimal getValor() {
        return decorado.getValor().add(TAXA_GARANTIA);
    }
}