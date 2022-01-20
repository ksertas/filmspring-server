package nl.serkanertas.filmspringserver.controller;

import nl.serkanertas.filmspringserver.exception.*;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> exception(UserNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = GroupNotFoundException.class)
    public ResponseEntity<Object> exception(GroupNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ResourceAlreadyExistsException.class)
    public ResponseEntity<Object> exception(ResourceAlreadyExistsException e) {
        // Http 303 'See Other' possible alternative
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = ActorNotFoundException.class)
    public ResponseEntity<Object> exception(ActorNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MediaNotFoundException.class)
    public ResponseEntity<Object> exception(MediaNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = InvalidCredentialsException.class)
    public ResponseEntity<Object> exception(InvalidCredentialsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> exception(BadRequestException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = UserNotAuthorizedException.class)
    public ResponseEntity<Object> exception(UserNotAuthorizedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = SizeLimitExceededException.class)
    public ResponseEntity<Object> exception(SizeLimitExceededException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.PAYLOAD_TOO_LARGE);
    }

}
