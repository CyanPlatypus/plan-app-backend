package com.plan.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CommentDto {

    private Integer id;
    //private Integer taskId;
    private String name;
    private String description;
    private double hours = 0.0;

    @JsonIgnore
    public String getNameFieldName(){
        return "name";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public Integer getTaskId() {
//        return taskId;
//    }
//
//    public void setTaskId(Integer taskId) {
//        this.taskId = taskId;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }
}
