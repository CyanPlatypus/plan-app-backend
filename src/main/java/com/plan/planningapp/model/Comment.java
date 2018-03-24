package com.plan.planningapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String name;

    @Lob //LongText
    private String description;

    private double hours = 0.0;

    @ManyToOne(optional = false,cascade={CascadeType.ALL})
    @JoinColumn(name="task_id")
    private Task task = null;

    @ManyToOne(optional = false,cascade={CascadeType.ALL})
    @JoinColumn(name="user_id")
    private User user = null;

    private LocalDateTime creationDateTime;

    public Comment(){
        creationDateTime = LocalDateTime.now();
    }

    //region getters&setters
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

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    //endregion


}
