package com.ads3.auto_center.core.exceptions;

import org.springframework.http.HttpStatus;

public class RuleValidationException extends BaseException {

    public RuleValidationException(String ruleName, String message) {
        super("Violação de Regra de Negócio: " + ruleName, message, HttpStatus.CONFLICT,
                Severity.ERROR, "BUSINESS_RULE_VIOLATION");
    }
}