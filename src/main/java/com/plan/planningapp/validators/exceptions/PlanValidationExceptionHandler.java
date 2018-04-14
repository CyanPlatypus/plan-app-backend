package com.plan.planningapp.validators.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

import static java.util.stream.Collectors.toList;

@ControllerAdvice
public class PlanValidationExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        BindingResult bindingResult = ex
                .getBindingResult();

        List<FieldValidationError> apiFieldErrors = bindingResult
                .getFieldErrors()
                .stream()
                .map(fieldError -> new FieldValidationError(
                        fieldError.getField(),
                        fieldError.getCode()//,
                        //fieldError.getRejectedValue()
                        )
                )
                .collect(toList());

//        List<GlobalValidationError> apiGlobalErrors = bindingResult
//                .getGlobalErrors()
//                .stream()
//                .map(globalError -> new GlobalValidationError(
//                        globalError.getDefaultMessage(),
//                        globalError.getCode()
//                        )
//                )
//                .collect(toList());

        //ApiErrorsView apiErrorsView = new ApiErrorsView(apiFieldErrors, apiGlobalErrors);

        return new ResponseEntity<>(apiFieldErrors, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
