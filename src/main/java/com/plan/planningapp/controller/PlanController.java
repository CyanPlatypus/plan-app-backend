package com.plan.planningapp.controller;

import com.plan.dto.UserCreateRequestDto;
import com.plan.planningapp.model.Task;
import com.plan.planningapp.security.UserInfoDetails;
import com.plan.planningapp.service.PlanUserDetailsService;
import com.plan.planningapp.service.UserService;
import com.plan.dto.TaskDto;
import com.plan.dto.UserDto;
import com.plan.planningapp.validators.UserCreateRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
@RequestMapping(path = "/plan")
public class PlanController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @GetMapping(path = "hello")
    public @ResponseBody String SayHello(){
        addTask(); return "Oh hi Mark";
    }

    public void addTask(){
        Task t = new Task("go to nell", "decr",
                null, LocalDateTime.now(), LocalDateTime.now(), 67);
        userService.addTaskToUserOwnedList(t, 9);
    }

    @CrossOrigin
    @GetMapping(path = "users")
    public @ResponseBody List<UserDto> GetUsers(){
        List<UserDto> list = new ArrayList<>();

        UserDto bob = new UserDto();
        bob.setName("Bob");
        //bob.setEmail("bluebird@bob.com");

        UserDto alice = new UserDto();
        alice.setName("Alice");
        //alice.setEmail("bluebird_2@bob.com");

        list.add(bob);
        list.add(alice);

        return list;
    }

//    @RequestMapping(value = "/usernamePrivc", method = RequestMethod.GET)
//    @ResponseBody
//    public String currentUserName(Principal principal) {
//        return principal.getName();
//    }





//    @GetMapping("/signup/getAuth")
//    @ResponseBody
//    public String greeting(@RequestParam("name") String username,
//                           @RequestParam("email") String email,
//                           @RequestParam("pass") String password) {
//        //todo validation check if this email exists
//        planUserDetailsService.addUser(username, email, password);
//        return "done";
//    }



    @RequestMapping(method=GET, value="/user/{id}/tasks" )
    public @ResponseBody Iterable<TaskDto> delete(@PathVariable Integer id) {
        return userService.getOwnedTasks(id);
    }

    @GetMapping(path = "test")
    public @ResponseBody    String GetTestUsers(){

//        User alice = new User();
//        alice.setName("Alice");
//
//        User bob = new User();
//        bob.setName("Bob");
//
//        userRepository.save(alice);
//        userRepository.save(bob);
//
//        Task t = new Task();
//        t.setName("be back");
//
//        alice.becomeAnOwnerOfTheTask(t);
//        bob.becomeAnAssineeToTheTask(t);
//
//        taskRepository.save(t);
//
//        Comment comm = new Comment();
//        comm.setDescription("help me");
//
//        alice.AddComment(comm);
//
//        t.AddComment(comm);
//
//        commentRepository.save(comm);
//
//        Task innerTask = new Task();
//
//        innerTask.AddInnerTask(innerTask);
//
//        innerTask.setOwnerUser(alice);
//
//        innerTask.AddAssigneeUser(alice);
//
//        taskRepository.save(innerTask);

////        Task test = taskRepository.findById(2).orElse(null);
////        Optional<Task> aThing = taskRepository.findById(2);
////        Task t = aThing.get();


        return "check for crash";
    }
}
