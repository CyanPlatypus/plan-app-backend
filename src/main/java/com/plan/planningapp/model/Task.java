package com.plan.planningapp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;
    @Lob //LongText
    private String description;

    @ManyToMany(mappedBy = "assignedTasks")
    private Set<User> assigneesUsers;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User ownerUser;

    public Task(){
        name = null;
        description = null;
        assigneesUsers = new HashSet<>();
        ownerUser = null;

    }

    //region get_set
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
    public Set<User> getAssigneesUsers() {
        return assigneesUsers;
    }

    public void setAssigneesUsers(Set<User> assigneesUsers) {
        this.assigneesUsers = assigneesUsers;
    }

    public User getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(User ownerUser) {
        this.ownerUser = ownerUser;
    }

    //endregion
}
