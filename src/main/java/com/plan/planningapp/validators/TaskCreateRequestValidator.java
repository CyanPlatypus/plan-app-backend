package com.plan.planningapp.validators;

import com.plan.dto.TaskDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TaskCreateRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return TaskDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object ob, Errors errors) {
        TaskDto taskDto = (TaskDto) ob;

        if(taskDto.getName().isEmpty() ||  taskDto.getName().trim().isEmpty()){
            errors.rejectValue(taskDto.getNameFieldName(), PlanError.EMPTY.getDescription());
        }

//        if(taskDto.getFinishDateTime().isBefore(taskDto.getStartDateTime()))
//            errors.rejectValue(taskDto.getFinishDateTimeFieldName(), PlanError.FINISHBEFORESTART.getDescription());
    }
}
