package com.plan.planningapp.validators;

public enum PlanError {

    EMPTY("field cannot be empty"),
    EXISTS("user already exists"),
    FINISHBEFORESTART("finish date cannot bw before starting");

    private String description;

    private PlanError(String descr){
        description = descr;
    }

    public String getDescription() {
        return description;
    }
}
