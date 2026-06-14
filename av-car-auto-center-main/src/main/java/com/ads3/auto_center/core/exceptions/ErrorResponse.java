package com.ads3.auto_center.core.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

    private String title;
    private String message;

    private ErrorResponse(String message) {
        this("Error", message);
    }

    private ErrorResponse(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public static ErrorResponse error(BaseException ex) {
        return new ErrorResponse(ex.getTitle(), ex.getMessage() + " -> " + ex.getMotive());
    }

    public static ErrorResponse error(String message) {
        return new ErrorResponse(message);
    }
}