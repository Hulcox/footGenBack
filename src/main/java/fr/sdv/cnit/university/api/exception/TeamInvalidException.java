package fr.sdv.cnit.university.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TeamInvalidException extends RuntimeException {

    public TeamInvalidException(String message) {
        super(message);
    }
}
