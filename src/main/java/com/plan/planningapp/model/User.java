package com.plan.planningapp.model;

import com.plan.planningapp.model.Task;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "`user`")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(unique=true)
    private String name;

    private String email;

    //@OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_task",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"))
    private Set<Task> assignedTasks = new HashSet<>();

    @OneToMany(mappedBy = "ownerUser", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Task> ownedTasks = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    public User() {

    }

    public void becomeAnAssineeToTheTask(Task t) {
        assignedTasks.add(t);
    }

    public void becomeAnOwnerOfTheTask(Task t) {
        t.setOwnerUser(this);
        ownedTasks.add(t);
    }

    public void RemoveTask(Task t){
        if(t.getOwnerUser() == this){
            this.ownedTasks.remove(t);
        }
    }

    public void AddComment(Comment c) {
        c.setUser(this);
        this.comments.add(c);
    }

    public void RemoveComment(Comment c) {
        this.comments.remove(c);
    }

    public void RemoveComment(int id) {
        this.comments.removeIf(com -> com.getId() == id);
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
