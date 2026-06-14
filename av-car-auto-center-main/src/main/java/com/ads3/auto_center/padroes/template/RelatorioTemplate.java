package com.ads3.auto_center.padroes.template;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public abstract class RelatorioTemplate<T> {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // Sequência fixa — subclasses não podem reordenar as etapas
    public final RelatorioDocumento<T> gerar() {
        return new RelatorioDocumento<>(
                montarCabecalho(),
                getTitulo(),
                montarCorpo(),
                montarRodape()
        );
    }

    protected abstract String getTitulo();

    protected abstract List<T> montarCorpo();

    protected String montarCabecalho() {
        return "Av Car Auto Center — " + getTitulo() +
               " | Gerado em: " + LocalDateTime.now().format(FMT);
    }

    protected String montarRodape() {
        return "Fim do relatório — " + getTitulo();
    }
}