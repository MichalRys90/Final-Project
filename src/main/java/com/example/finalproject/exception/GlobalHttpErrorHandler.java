package com.example.finalproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(UserWithGivenUserNameExist.class)
    public ResponseEntity<Object> handleUserWithGivenUserNameExist(UserWithGivenUserNameExist exception) {
        return new ResponseEntity<>("Username is taken, choose another one", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserDoesntExist.class)
    public ResponseEntity<Object> handleUserDoesntExist(UserDoesntExist exception) {
        return new ResponseEntity<>("invalid login or password", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PatientWithGivenIdDoesntExist.class)
    public ResponseEntity<Object> handlePatientDoesntExist(PatientWithGivenIdDoesntExist exception) {
        return new ResponseEntity<>("Patient with given id doesnt exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserWithGivenIdDoesntExist.class)
    public ResponseEntity<Object> handleUserWithGivenIdDoesntExist(UserWithGivenIdDoesntExist exception) {
        return new ResponseEntity<>("User with given id doesnt exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PatientExist.class)
    public ResponseEntity<Object> handlePatientExist(PatientExist exception) {
        return new ResponseEntity<>("The patient already exists", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GuardianExist.class)
    public ResponseEntity<Object> handleGuardianExist(GuardianExist exception) {
        return new ResponseEntity<>("The guardian already exists", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GuardianWithGivenIdDoesntExist.class)
    public ResponseEntity<Object> handleGuardianWithGivenIdDoesntExist(GuardianWithGivenIdDoesntExist exception) {
        return new ResponseEntity<>("Guardian with given id doesnt exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PatientIsAlreadyAdded.class)
    public ResponseEntity<Object> PatientIsAlreadyAddedException(PatientIsAlreadyAdded exception) {
        return new ResponseEntity<>("Patient is already added", HttpStatus.BAD_REQUEST);
    }
}
