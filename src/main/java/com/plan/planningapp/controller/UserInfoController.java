package com.plan.planningapp.controller;

import com.plan.dto.TaskDto;
import com.plan.dto.UserCreateRequestDto;
import com.plan.dto.UserDto;
import com.plan.planningapp.security.UserInfoDetails;
import com.plan.planningapp.service.PlanUserDetailsService;
import com.plan.planningapp.validators.UserCreateRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserInfoController {

    @Autowired
    private PlanUserDetailsService planUserDetailsService;

    @Autowired
    private UserCreateRequestValidator userCreateRequestValidator;

    @InitBinder("userCreateRequestDto")
    public void setupBinder(WebDataBinder binder) {
        binder.addValidators(userCreateRequestValidator);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseBody
    public String add(@Valid @RequestBody UserCreateRequestDto userCreateRequestDto) {
        if(userCreateRequestDto!=null)
            planUserDetailsService.addUser(userCreateRequestDto.getName(),
                    userCreateRequestDto.getEmail(), userCreateRequestDto.getPass());
        return "we've done our best";
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Authentication authentication) {
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();
        return planUserDetailsService.getUserNameByUserInfoId(userInfoDetails.getUiId());
    }


}