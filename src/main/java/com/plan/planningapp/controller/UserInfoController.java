package com.plan.planningapp.controller;

import com.plan.dto.TaskDto;
import com.plan.dto.UserCreateRequestDto;
import com.plan.dto.UserDto;
import com.plan.planningapp.security.UserInfoDetails;
import com.plan.planningapp.service.PlanUserDetailsService;
import com.plan.planningapp.validators.UserCreateRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity addUser(@Valid @RequestBody UserCreateRequestDto userCreateRequestDto) {
        if(userCreateRequestDto!=null)
            planUserDetailsService.addUser(userCreateRequestDto.getName(),
                    userCreateRequestDto.getEmail(), userCreateRequestDto.getPass());
        return ResponseEntity.status(HttpStatus.OK).body(userCreateRequestDto.getName());
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity currentUserName(Authentication authentication) {
        UserInfoDetails userInfoDetails = (UserInfoDetails) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.OK)
                .body(planUserDetailsService.getUserNameByUserInfoId(userInfoDetails.getUiId()));
    }


}
