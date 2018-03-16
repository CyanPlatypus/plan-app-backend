package com.plan.planningapp.repositories;

import com.plan.planningapp.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

}