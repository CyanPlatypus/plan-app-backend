package dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

public class TaskDto {

    private Integer id;
    private String name;
    private String description;
    @JsonIgnore
    private TaskDto parentTask;

    private Set<TaskDto> innerTasks = new HashSet<>();

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


}
