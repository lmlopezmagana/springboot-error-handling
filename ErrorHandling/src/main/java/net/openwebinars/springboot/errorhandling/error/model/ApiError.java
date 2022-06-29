package net.openwebinars.springboot.errorhandling.error.model;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;


public interface ApiError {

    HttpStatus getStatus();
    int getStatusCode();
    String getMessage();
    String getPath();
    LocalDateTime getDate();
    List<ApiSubError> getSubErrors();


}