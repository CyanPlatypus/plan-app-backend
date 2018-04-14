package com.plan.dto;

import java.util.HashSet;
import java.util.Set;

public class UserDto {

    //private Integer id;
    protected String name;

    //protected String email;

    private Set<TaskDto> ownedTasks = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TaskDto> getOwnedTasks() {
        return ownedTasks;
    }

    public void setOwnedTasks(Set<TaskDto> ownedTasks) {
        this.ownedTasks = ownedTasks;
    }
}