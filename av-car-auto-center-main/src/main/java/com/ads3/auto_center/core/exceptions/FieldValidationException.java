package com.ads3.auto_center.core.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FieldValidationException extends BaseException {

    private final String field;

    public FieldValidationException(String field, String message) {
        super("Erro de Validação de Campo", message, HttpStatus.BAD_REQUEST, Severity.WARNING,
                "FIELD_VALIDATION_ERROR");
        this.field = field;
    }
}