package com.plan.planningapp.validators;

import com.plan.planningapp.model.additional.UserCreateRequest;
import com.plan.planningapp.repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserCreateRequestValidator implements Validator {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserCreateRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object ob, Errors errors) {
        UserCreateRequest productCreateRequest = (UserCreateRequest) ob;

        if (userInfoRepository.findByEmail(((UserCreateRequest) ob).getEmail())!=null) {
            errors.reject("CODE","EXISTS  "/*ALREADY_EXISTS.getCode()*/);
        }
    }
}
