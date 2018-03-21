package dto;

import java.util.HashSet;
import java.util.Set;

public class TaskDto {

    private String name;
    private String description;

    private Set<TaskDto> innerTasks = new HashSet<>();

    public Set<TaskDto> getInnerTasks() {
        return innerTasks;
    }

    public void setInnerTasks(Set<TaskDto> innerTasks) {
        this.innerTasks = innerTasks;
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
