package com.plan.planningapp.service;

import com.plan.dto.CommentDto;
import com.plan.planningapp.model.Comment;
import com.plan.planningapp.repositories.CommentRepository;
import com.plan.planningapp.repositories.TaskRepository;
import com.plan.planningapp.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;

@Service
public class TaskCommentService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    @Qualifier("modelMapperBean")
    private ModelMapper modelMapper;


    public Iterable<CommentDto> getComments(Integer id) {
        Iterable<Comment> comms = commentRepository.findByTaskId(id);
        ArrayList<CommentDto> commDtos = new ArrayList<>();

        for (Comment com: comms) {
            CommentDto cd = modelMapper.map(com, CommentDto.class);
            cd.setName(com.getUserInfo());
            commDtos.add(cd);
        }

        return commDtos;
    }

    public void addComment(Integer taskId, Integer userId, CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto, Comment.class);

        userRepository.findById(userId).ifPresent(
                user->
        taskRepository.findById(taskId).ifPresent(
                task->{
                    comment.setCreationDateTimeNow();
                    comment.setId(null);
                    comment.setUser(user);
                    task.addComment(comment);
                    taskRepository.save(task);
                }
        ));
    }


    public boolean userIsCommentOwner(Integer id, Integer uId) {
        if(commentRepository.getFirstByIdAndUserId(id, uId)!=null)
            return true;
        return false;
    }

    public void removeComment(Integer id) {
        commentRepository.deleteById(id);
    }
}
