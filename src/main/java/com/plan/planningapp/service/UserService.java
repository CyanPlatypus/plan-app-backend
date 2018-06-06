package com.plan.planningapp.service;

import com.plan.planningapp.model.Task;
import com.plan.planningapp.model.User;
import com.plan.planningapp.repositories.TaskRepository;
import com.plan.planningapp.repositories.UserRepository;
import com.plan.dto.TaskDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    @Qualifier("modelMapperBean")
    private ModelMapper modelMapper;

    public void addUser(User u){
        userRepository.save(u);
    }

    public void addTaskToUserOwnedList(Task t, Integer id){

        //return Optional.ofNullable(person)
                //.map(p -> p.getLogo())
                //.orElse(new DefaultLogo());

        userRepository.findById(id).ifPresent(user->{
            user.becomeAnOwnerOfTheTask(t); userRepository.save(user);
        });
        //Optional<User> op = userRepository.findById(id).map(user->user.becomeAnOwnerOfTheTask(t));
        //.save(u);
    }

    public Integer addTaskToTheUserOwnedList(TaskDto tDto, Integer id){
        //ModelMapper modelMapper = new ModelMapper();
        Task t = modelMapper.map(tDto, Task.class);
        t.setId(null);
//        userRepository.findById(id).ifPresent(user->{
//            user.becomeAnOwnerOfTheTask(t); userRepository.save(user);
//            taskRepository.save(t);
//        });

        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isPresent())
        {
            optUser.get().becomeAnOwnerOfTheTask(t);
            return taskRepository.save(t).getId();
        }

        return t.getId();
    }

    public void editTask(TaskDto tDto, Integer userId){
        Optional<Task> task = taskRepository.findById(tDto.getId());

        task.ifPresent(t->{
            t.setName( tDto.getName());
            t.setActualHours(tDto.getActualHours());
            t.setPlannedHours(tDto.getPlannedHours());
            t.setDescription(tDto.getDescription());

            taskRepository.save(t);
        });

       /* Task t = modelMapper.map(tDto, Task.class);

        userRepository.findById(userId).ifPresent(user->{
            t.setOwnerUser(user);
            taskRepository.save(t);
        });*/

    }

    public TaskDto getTask(Integer id){
        Task t = taskRepository.findById(id).orElse(null);
        if(t!=null){
            return modelMapper.map(t, TaskDto.class);
        }
        return null;
    }


    public void createMockUserWithTask(){
        User alice = new User();
        alice.setName("mock user Alice");
        Task task = new Task();
        task.setName("mock task");
        alice.becomeAnOwnerOfTheTask(task);
        userRepository.save(alice);
    }

    public Iterable<TaskDto> getOwnedTasks(Integer id ) {
        //ModelMapper modelMapper = new ModelMapper();


        Iterable<Task> tasks = taskRepository.findByOwnerUserId(id);
        ArrayList<TaskDto> tasksDto = new ArrayList<>();

        tasks.forEach(t->{
        if(t.getParentTask() == null )
            tasksDto.add(modelMapper.map(t, TaskDto.class));
        });

        return tasksDto;
        //return findByOwnerUserId(id);
    }

    @Bean
    public static ModelMapper modelMapperBean()
    {
        return new ModelMapper();
    }

    public boolean userIsTaskOwner(Integer id, Integer uId) {
        if (taskRepository.getFirstByIdAndOwnerUserId(id, uId)!=null)
            return true;
        return false;
    }

    public void removeTask(Integer id) {
        taskRepository.deleteById(id);
    }

//    Iterable<Task> findByOwnerUserId(Integer id){
//        Iterable<Task> tasks = taskRepository.findAll();
//        ArrayList<Task> tas = new ArrayList<>();
//        tasks.forEach(t-> {if (t.getOwnerUser().getId() == id) tas.add(t);});
//        return tas;
//    }

}
