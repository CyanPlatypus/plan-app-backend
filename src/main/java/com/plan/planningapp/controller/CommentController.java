package com.plan.planningapp.controller;

import com.plan.dto.CommentDto;
import com.plan.planningapp.security.UserInfoDetails;
import com.plan.planningapp.service.PlanUserDetailsService;
import com.plan.planningapp.service.TaskCommentService;
import com.plan.planningapp.validators.CommentCreateRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CommentController {

    @Autowired
    private PlanUserDetailsService planUserDetailsService;

    @Autowired
    private TaskCommentService taskCommentService;

    @Autowired
    private CommentCreateRequestValidator commentCreateRequestValidator;

    @InitBinder("commentDto")
    public void setupBinder(WebDataBinder binder) {
        binder.addValidators(commentCreateRequestValidator);
    }

    @RequestMapping(value = "/tasks/{id}/comments", method = RequestMethod.GET)
    @ResponseBody
    public  Iterable<CommentDto> getComments(@PathVariable Integer id, Authentication authentication){
        return taskCommentService.getComments(id);
    }

    @RequestMapping(value = "/tasks/{id}/comments/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addComment(@Valid @RequestBody CommentDto commentDto,
                                     @PathVariable Integer id, Authentication authentication){
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();

        taskCommentService.addComment(id, userInfoDetails.getUId(), commentDto);
        return ResponseEntity.status(HttpStatus.OK).body("Added");
    }

    @RequestMapping(value = "/tasks/{taskId}/comments/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity removeComment(@PathVariable Integer taskId,
                                        @PathVariable Integer id, Authentication authentication){
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();

        if (taskCommentService.userIsCommentOwner(id, userInfoDetails.getUId())){
            taskCommentService.removeComment(id);
            return ResponseEntity.status(HttpStatus.OK).body("Removed");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not removed");
    }
}
