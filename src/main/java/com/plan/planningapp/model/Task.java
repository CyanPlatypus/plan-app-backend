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
    private Set<User> assigneesUsers= new HashSet<>();

    @ManyToOne(/*fetch = FetchType.EAGER*/)
    @JoinColumn(name = "user_id")
    private User ownerUser = null;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="parent_task_id")
    private Task parentTask = null;

    @OneToMany(mappedBy="parentTask")
    private Set<Task> innerTasks = new HashSet<>();

    @OneToMany(mappedBy="task")
    private Set<Comment> comments = new HashSet<>();

    public Task(){
        name = null;
        description = null;
    }

    public void AddInnerTask(Task t){
        t.setParentTask(this);
        this.innerTasks.add(t);
    }

    public void RemoveInnerTask(Task t){
        this.innerTasks.remove(t);
    }
    public void RemoveInnerTask(int id){
        this.innerTasks.removeIf(task->task.getId()==id);
    }

    public void AddAssigneeUser(User u){
        this.assigneesUsers.add(u);
    }

    public void RemoveAssigneeUser(User u){
        this.assigneesUsers.remove(u);
    }
    public void RemoveAssigneeUser(int id){
        this.assigneesUsers.removeIf(user->user.getId()==id);
    }

    public void AddComment(Comment c){
        c.setTask(this);
        this.comments.add(c);
    }

    public void RemoveComment(Comment c){
        this.comments.remove(c);
    }
    public void RemoveComment(int id){
        this.comments.removeIf(com->com.getId()==id);
    }

    //region get_set
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Task getParentTask() {
        return parentTask;
    }

    public void setParentTask(Task parentTask) {
        this.parentTask = parentTask;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
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

    public Set<Task> getInnerTasks() {
        return innerTasks;
    }

    public void setInnerTasks(Set<Task> innerTasks) {
        this.innerTasks = innerTasks;
    }

    //endregion
}
