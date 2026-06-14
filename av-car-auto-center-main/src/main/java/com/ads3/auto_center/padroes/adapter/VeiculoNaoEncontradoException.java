package com.ads3.auto_center.padroes.adapter;

public class VeiculoNaoEncontradoException extends RuntimeException {

    public VeiculoNaoEncontradoException(String placa) {
        super("Veículo com placa '" + placa + "' não encontrado.");
    }

    public VeiculoNaoEncontradoException(String placa, String detalhe) {
        super("Veículo com placa '" + placa + "' não encontrado. " + detalhe);
    }
}