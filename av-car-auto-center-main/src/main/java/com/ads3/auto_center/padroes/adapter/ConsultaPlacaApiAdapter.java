package com.ads3.auto_center.padroes.adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class ConsultaPlacaApiAdapter implements ConsultaVeiculoPort {

    // Aceita padrão antigo (ABC1234 ou ABC-1234) e Mercosul (ABC1D23)
    private static final String REGEX_PLACA = "[A-Z]{3}[-]?\\d{4}|[A-Z]{3}\\d[A-Z]\\d{2}";

    private final RestTemplate restTemplate;

    @Value("${consulta.placa.url:http://localhost}")
    private String baseUrl;

    @Value("${consulta.placa.token:token-nao-configurado}")
    private String token;

    public ConsultaPlacaApiAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public DadosVeiculo consultarPorPlaca(String placa) {
        String placaNormalizada = normalizar(placa);
        validarFormato(placaNormalizada);

        String url = baseUrl + "/" + placaNormalizada;
        HttpEntity<Void> requisicao = criarRequisicao();

        try {
            ResponseEntity<ConsultaPlacaApiResponse> resposta =
                    restTemplate.exchange(url, HttpMethod.GET, requisicao, ConsultaPlacaApiResponse.class);

            if (resposta.getStatusCode() == HttpStatus.NOT_FOUND || resposta.getBody() == null) {
                throw new VeiculoNaoEncontradoException(placaNormalizada);
            }

            return adaptar(resposta.getBody());

        } catch (VeiculoNaoEncontradoException e) {
            throw e;
        } catch (HttpClientErrorException.NotFound e) {
            throw new VeiculoNaoEncontradoException(placaNormalizada);
        } catch (RestClientException e) {
            throw new ConsultaIndisponivelException(e);
        }
    }

    private void validarFormato(String placa) {
        if (placa == null || !placa.matches(REGEX_PLACA)) {
            throw new VeiculoNaoEncontradoException(
                    placa,
                    "Use o formato antigo (ABC1234 ou ABC-1234) ou Mercosul (ABC1D23)."
            );
        }
    }

    private String normalizar(String placa) {
        if (placa == null) return null;
        return placa.trim().toUpperCase().replace("-", "");
    }

    private HttpEntity<Void> criarRequisicao() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        return new HttpEntity<>(headers);
    }

    private DadosVeiculo adaptar(ConsultaPlacaApiResponse r) {
        return new DadosVeiculo(
                r.placa(),
                r.marca(),
                r.modelo(),
                r.ano(),
                r.cor(),
                r.uf(),
                r.municipio(),
                r.situacao()
        );
    }
}