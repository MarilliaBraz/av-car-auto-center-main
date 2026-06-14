package com.ads3.auto_center.core.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException {

    private final String title;
    private final HttpStatus httpStatus;
    private final Severity severity;
    private final String motive;

    public BaseException(String title, String message, HttpStatus httpStatus, Severity severity, String motive) {
        super(message);
        this.title = title;
        this.httpStatus = httpStatus;
        this.severity = severity;
        this.motive = motive;
    }
}