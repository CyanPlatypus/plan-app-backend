package com.plan.planningapp.controller;

import dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/plan")
public class PlanController {

    @CrossOrigin
    @GetMapping(path = "hello")
    public @ResponseBody String SayHello(){
        return "Oh hi Mark";
    }

    @CrossOrigin
    @GetMapping(path = "users")
    public @ResponseBody List<UserDto> GetUsers(){
        List<UserDto> list = new ArrayList<>();

        UserDto bob = new UserDto();
        bob.setFirstName("Bob");
        bob.setEmail("bluebird@bob.com");

        UserDto alice = new UserDto();
        alice.setFirstName("Alice");
        alice.setEmail("bluebird_2@bob.com");

        list.add(bob);
        list.add(alice);

        return list;
    }
}
