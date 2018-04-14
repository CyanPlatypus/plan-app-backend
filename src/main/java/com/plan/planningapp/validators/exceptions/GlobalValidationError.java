package com.plan.planningapp.validators.exceptions;

public class GlobalValidationError {

    private String defaultMessage;
    private String code;

    public GlobalValidationError(String defaultMessage, String code) {
        this.defaultMessage = defaultMessage;
        this.code = code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public String getCode() {
        return code;
    }
}
