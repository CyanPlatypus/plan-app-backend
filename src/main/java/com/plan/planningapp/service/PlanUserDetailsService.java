package com.plan.planningapp.service;

import com.plan.dto.UserDto;
import com.plan.planningapp.model.User;
import com.plan.planningapp.model.UserInfo;
import com.plan.planningapp.repositories.UserInfoRepository;
import com.plan.planningapp.repositories.UserRepository;
import com.plan.planningapp.security.UserInfoDetails;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PlanUserDetailsService implements UserDetailsService{
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserInfo ui = userInfoRepository.findByEmail(email);

        if(ui == null)
            return null;
        else{
            return new UserInfoDetails(ui);
        }
    }

    public String getUserNameByUserInfoId(Integer id){

        UserInfo ui = userInfoRepository.findById(id).orElse(null);
        if(ui!=null)
        {
            User user = ui.getUser();
            if(user!=null)
                return user.getName();
        }

        return "fairytale goes bad";
    }

    public UserDto getUserByUserInfoId(Integer id){

        UserInfo ui = userInfoRepository.findById(id).orElse(null);
        if(ui!=null)
        {
            User user = ui.getUser();
            if(user!=null){
                ModelMapper modelMapper = new ModelMapper();
                return  modelMapper.map(user, UserDto.class);
            }
        }

        return null;
    }

    public void addUser(String name, String email, String pass){
        UserInfo ui = new UserInfo();
        ui.setEmail(email);
        ui.setPass(pass);

        User user = new User();
        user.setName(name);

        userRepository.save(user);

        ui.setUser(user);

        userInfoRepository.save(ui);
    }
}
