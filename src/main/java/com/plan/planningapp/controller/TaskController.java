package com.plan.planningapp.controller;

import com.plan.dto.TaskDto;
import com.plan.dto.UserDto;
import com.plan.planningapp.security.UserInfoDetails;
import com.plan.planningapp.service.PlanUserDetailsService;
import com.plan.planningapp.service.UserService;
import com.plan.planningapp.validators.TaskCreateRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TaskController {

    @Autowired
    private PlanUserDetailsService planUserDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskCreateRequestValidator taskCreateRequestValidator;

    @InitBinder("taskDto")
    public void setupBinder(WebDataBinder binder) {
        binder.addValidators(taskCreateRequestValidator);
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<TaskDto> getTasks(Authentication authentication){
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();

        UserDto userDto = planUserDetailsService.getUserByUserInfoId(userInfoDetails.getUiId());
        if (userDto!=null)
            return userDto.getOwnedTasks();
        return null;
    }

    @RequestMapping(value = "/tasks/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addTask(@Valid @RequestBody TaskDto taskDto, Authentication authentication) {
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();

        if(taskDto!=null)
//            userService.addTaskToTheUserOwnedList(taskDto,
//                    planUserDetailsService.getUserIdByUserInfoId(userInfoDetails.getUiId()));
            userService.addTaskToTheUserOwnedList(taskDto,userInfoDetails.getUId());
        //return "task added";
        return ResponseEntity.status(HttpStatus.OK).body("Added");
    }

    @RequestMapping(value = "/tasks/edit", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity editTask(@Valid @RequestBody TaskDto taskDto, Authentication authentication) {
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();

        if(taskDto!=null)
            userService.editTask(taskDto, userInfoDetails.getUId());

        return  ResponseEntity.status(HttpStatus.OK).body("Edited");
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<TaskDto> getTask(@PathVariable Integer id, Authentication authentication) {
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();

        TaskDto t = userService.getTask(id);
        if(t!=null)
            return ResponseEntity.ok(t);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity removeTask(@PathVariable Integer id, Authentication authentication){
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        if(userService.userIsTaskOwner(id, userInfoDetails.getUId())){
            userService.removeTask(id);
            return ResponseEntity.status(HttpStatus.OK).body("Removed");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not removed");
    }
}
