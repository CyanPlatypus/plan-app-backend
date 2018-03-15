package com.plan.planningapp.repositories;

import com.plan.planningapp.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
