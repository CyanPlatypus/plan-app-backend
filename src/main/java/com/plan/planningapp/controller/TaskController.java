package com.plan.planningapp.controller;

import com.plan.dto.TaskDto;
import com.plan.dto.UserDto;
import com.plan.planningapp.security.UserInfoDetails;
import com.plan.planningapp.service.PlanUserDetailsService;
import com.plan.planningapp.service.UserService;
import com.plan.planningapp.validators.TaskCreateRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/addTask", method = RequestMethod.POST)
    @ResponseBody
    public String addTask(@Valid @RequestBody TaskDto taskDto, Authentication authentication) {
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();

        if(taskDto!=null)
//            userService.addTaskToTheUserOwnedList(taskDto,
//                    planUserDetailsService.getUserIdByUserInfoId(userInfoDetails.getUiId()));
            userService.addTaskToTheUserOwnedList(taskDto,userInfoDetails.getUId());
        return "task added";
    }
}
