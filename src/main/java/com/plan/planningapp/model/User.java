package com.plan.planningapp.model;

import com.plan.planningapp.model.Task;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;

    private String email;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_task",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"))
    private Set<Task> assignedTasks;

    @OneToMany(mappedBy = "ownerUser", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Task> ownedTasks;

    public User(){
        name = null;
        email = null;
        assignedTasks = new HashSet<>();
        ownedTasks = new HashSet<>();

    }

    public void becomeAnAssineeToTheTask(Task t){
        assignedTasks.add(t);
    }

    public void becomeAnOwnerOfTheTask(Task t){
        t.setOwnerUser(this);
        ownedTasks.add(t);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Set<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(Set<Task> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }
    public Set<Task> getOwnedTasks() {
        return ownedTasks;
    }

    public void setOwnedTasks(Set<Task> ownedTasks) {
        this.ownedTasks = ownedTasks;
    }
    //endregion

}
