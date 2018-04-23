package com.plan.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class TaskDto {

    private Integer id;
    private String name;
    private String description;
//    private LocalDateTime startDateTime;
//    private LocalDateTime finishDateTime;
    private double plannedHours=0.0;
    private double actualHours=0.0;
    private boolean isFinished = false;

    @JsonIgnore
    private TaskDto parentTask;

    private Set<TaskDto> innerTasks = new HashSet<>();

    @JsonIgnore
    public String getNameFieldName(){
        return "name";
    }
    @JsonIgnore
    public String getFinishDateTimeFieldName(){
        return "finishDateTime";
    }

    @JsonIgnore
    public Integer getParentID() {
        return parentTask == null? null:parentTask.getId();
    }


    public Set<TaskDto> getInnerTasks() {
        return innerTasks;
    }

    public void setInnerTasks(Set<TaskDto> innerTasks) {
        this.innerTasks = innerTasks;
    }

    public TaskDto getParentTask() {
        return parentTask;
    }

    public void setParentTask(TaskDto parentTask) {
        this.parentTask = parentTask;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

//    public LocalDateTime getStartDateTime() {
//        return startDateTime;
//    }
//
//    public void setStartDateTime(LocalDateTime startDateTime) {
//        this.startDateTime = startDateTime;
//    }
//
//    public LocalDateTime getFinishDateTime() {
//        return finishDateTime;
//    }
//
//    public void setFinishDateTime(LocalDateTime finishDateTime) {
//        this.finishDateTime = finishDateTime;
//    }

    public double getPlannedHours() {
        return plannedHours;
    }

    public void setPlannedHours(double plannedHours) {
        this.plannedHours = plannedHours;
    }

    public double getActualHours() {
        return actualHours;
    }

    public void setActualHours(double actualHours) {
        this.actualHours = actualHours;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}
