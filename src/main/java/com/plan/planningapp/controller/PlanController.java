package com.plan.planningapp.controller;

import com.plan.planningapp.model.Task;
import com.plan.planningapp.model.User;
import com.plan.planningapp.repositories.TaskRepository;
import com.plan.planningapp.repositories.UserRepository;
import dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping(path = "/plan")
public class PlanController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

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

    @GetMapping(path = "test")
    public @ResponseBody    String GetTestUsers(){

        User alice = new User();
        alice.setName("Alice");

        User bob = new User();
        bob.setName("Bob");

        userRepository.save(alice);
        userRepository.save(bob);

        Task t = new Task();
        t.setName("be back");

        alice.becomeAnOwnerOfTheTask(t);
        bob.becomeAnAssineeToTheTask(t);

        taskRepository.save(t);

        //Task test = taskRepository.findById(2).orElse(null);
        //Optional<Task> aThing = taskRepository.findById(2);
        //Task t = aThing.get();


        return "check for crash";
    }
}
