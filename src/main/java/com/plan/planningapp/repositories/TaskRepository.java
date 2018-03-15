package com.plan.planningapp.repositories;

import com.plan.planningapp.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Integer> {
}
