package com.ads3.auto_center.core.exceptions;

import com.ads3.auto_center.padroes.adapter.ConsultaIndisponivelException;
import com.ads3.auto_center.padroes.adapter.VeiculoNaoEncontradoException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException ex) {
        if (ex.getCause() != null) {
            log.error("BusinessException causa raiz: {}", ex.getCause().getMessage(), ex.getCause());
        }
        return ResponseEntity.status(ex.getHttpStatus()).body(ErrorResponse.error(ex));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(404).body(ErrorResponse.error("Registro relacionado não encontrado. Verifique os IDs informados."));
    }

    @ExceptionHandler(VeiculoNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleVeiculoNaoEncontrado(VeiculoNaoEncontradoException ex) {
        return ResponseEntity.status(404).body(ErrorResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(ConsultaIndisponivelException.class)
    public ResponseEntity<ErrorResponse> handleConsultaIndisponivel(ConsultaIndisponivelException ex) {
        log.warn("Serviço de consulta de placa indisponível: {}", ex.getMessage());
        return ResponseEntity.status(503).body(ErrorResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        log.error("Erro interno não tratado: ", ex);
        return ResponseEntity.status(500).body(ErrorResponse.error("Ocorreu um erro interno no servidor."));
    }
}