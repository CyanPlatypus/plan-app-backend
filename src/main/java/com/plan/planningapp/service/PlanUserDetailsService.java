package com.plan.planningapp.service;

import com.plan.planningapp.model.UserInfo;
import com.plan.planningapp.repositories.UserInfoRepository;
import com.plan.planningapp.repositories.UserRepository;
import com.plan.planningapp.security.UserInfoDetails;
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
}
