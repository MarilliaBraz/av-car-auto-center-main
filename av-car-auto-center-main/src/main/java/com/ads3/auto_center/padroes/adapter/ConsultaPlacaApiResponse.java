package com.ads3.auto_center.padroes.adapter;

public record ConsultaPlacaApiResponse(
        String placa,
        String marca,
        String modelo,
        String ano,
        String cor,
        String uf,
        String municipio,
        String situacao
) {}