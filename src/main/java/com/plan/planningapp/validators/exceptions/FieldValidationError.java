package com.plan.planningapp.validators.exceptions;

public class FieldValidationError {

    private String field;
    private String code;

    public FieldValidationError(String field, String code) {
        this.field = field;
        this.code = code;
    }

    public String getField() {
        return field;
    }

    public String getCode() {
        return code;
    }
}
