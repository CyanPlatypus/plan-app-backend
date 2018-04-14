package com.plan.planningapp.validators;

import com.plan.dto.UserCreateRequestDto;
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
        return UserCreateRequestDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object ob, Errors errors) {
        UserCreateRequestDto userCreateRequest = (UserCreateRequestDto) ob;

        if(userCreateRequest.getName().isEmpty() ||  userCreateRequest.getName().trim().isEmpty()){
            errors.rejectValue(UserCreateRequestDto.getNameFieldName(), PlanError.EMPTY.getDescription());
        }
        if(userCreateRequest.getEmail().isEmpty() ||  userCreateRequest.getEmail().trim().isEmpty()){
            errors.rejectValue(UserCreateRequestDto.getEmailFieldName(), PlanError.EMPTY.getDescription());
        }
        else if (userInfoRepository.findByEmail(((UserCreateRequestDto) ob).getEmail())!=null) {
            //errors.reject("CODE",PlanError.EXISTS.getDescription());
            errors.rejectValue(UserCreateRequestDto.getEmailFieldName(), PlanError.EXISTS.getDescription());
        }

        if(userCreateRequest.getPass().isEmpty() ||  userCreateRequest.getPass().trim().isEmpty()){
            errors.rejectValue(UserCreateRequestDto.getPassFieldName(), PlanError.EMPTY.getDescription());
        }


    }
}
