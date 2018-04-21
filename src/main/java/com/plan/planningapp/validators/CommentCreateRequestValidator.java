package com.plan.planningapp.validators;

import com.plan.dto.CommentDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CommentCreateRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return CommentDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object ob, Errors errors) {
        CommentDto commentDto = (CommentDto) ob;

        if(commentDto.getName().isEmpty() ||  commentDto.getName().trim().isEmpty()){
            errors.rejectValue(commentDto.getNameFieldName(), PlanError.EMPTY.getDescription());
        }

//        if(commentDto.getFinishDateTime().isBefore(commentDto.getStartDateTime()))
//            errors.rejectValue(commentDto.getFinishDateTimeFieldName(), PlanError.FINISHBEFORESTART.getDescription());
    }
}
