package com.plan.planningapp.model;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;
    @Lob //LongText
    private String description;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="task_id")
    private Task task = null;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="user_id")
    private User user = null;



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
    //endregion


}
