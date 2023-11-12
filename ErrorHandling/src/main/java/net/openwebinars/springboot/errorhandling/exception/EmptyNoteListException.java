package net.openwebinars.springboot.errorhandling.exception;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import java.net.URI;
import java.time.Instant;

public class EmptyNoteListException extends ErrorResponseException {

    public EmptyNoteListException() {
        super(HttpStatus.NOT_FOUND, of("No hay notas con ese criterio de búsqueda"), null);
    }

    public static ProblemDetail of(String message) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, message);
        problemDetail.setTitle("Notes Not Found");
        problemDetail.setType(URI.create("https://api.midominio.com/errors/not-found"));
        problemDetail.setProperty("entityType", "Note");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }


}