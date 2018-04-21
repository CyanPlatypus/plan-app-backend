package com.plan.planningapp.repositories;

import com.plan.planningapp.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

    Iterable<Comment> findByTaskId(Integer id);
    Comment getFirstByIdAndUserId(Integer id, Integer userId);
}