package com.example.demo.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.Getter;
import lombok.Setter;

import org.springframework.http.HttpStatus;


public class ControllerWithValidation {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @Getter
    @Setter
    public class Violation {
     
        private final String fieldName;
        private final String message;

        public Violation(String fieldName, String message){
            this.fieldName = fieldName;
            this.message = message;
        }
     
    }

    public class ValidationErrorResponse {

        private List<Violation> violations = new ArrayList<>();

        public List<Violation> getViolations() {
            return violations;
        }

        public void setViolations(List<Violation> violations) {
            this.violations = violations;
        }
     
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse onConstraintValidationException( ConstraintViolationException e) {
            ValidationErrorResponse error = new ValidationErrorResponse();
            for (ConstraintViolation violation : e.getConstraintViolations()) {
                error.getViolations().add(
                new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
            }
        return error;
    }


}
