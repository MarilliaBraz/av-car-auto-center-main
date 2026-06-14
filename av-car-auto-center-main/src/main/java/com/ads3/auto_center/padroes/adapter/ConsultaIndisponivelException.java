package com.ads3.auto_center.padroes.adapter;

public class ConsultaIndisponivelException extends RuntimeException {

    public ConsultaIndisponivelException(Throwable causa) {
        super("Serviço de consulta de placa indisponível no momento.", causa);
    }
}