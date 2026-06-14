package com.ads3.auto_center.padroes.template;

import java.util.List;

public record RelatorioDocumento<T>(
        String cabecalho,
        String titulo,
        List<T> corpo,
        String rodape
) {}