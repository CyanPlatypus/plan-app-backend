package com.plan.planningapp.repositories;

import com.plan.planningapp.model.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface TaskRepository extends CrudRepository<Task, Integer> {

    Iterable<Task> findByOwnerUserId(Integer id);
    Task getFirstByIdAndOwnerUserId(Integer id, Integer ownerUserId);

}
