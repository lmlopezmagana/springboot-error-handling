package net.openwebinars.springboot.errorhandling.error;

import jakarta.persistence.EntityNotFoundException;
import net.openwebinars.springboot.errorhandling.exception.NoteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Instant;




@RestControllerAdvice
public class GlobalRestControllerAdvice extends ResponseEntityExceptionHandler {


    /*
    @ExceptionHandler({NoteNotFoundException.class, EmptyNoteListException.class})
    public ProblemDetail handleNotFoundException(EntityNotFoundException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
        problemDetail.setTitle("Entity not found");
        problemDetail.setType(URI.create("https://api.midominio.com/errors/not-found"));
        problemDetail.setProperty("entityType", "Note");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
*/

    @ExceptionHandler({NoteNotFoundException.class})
    public ErrorResponse handleNotFoundException(EntityNotFoundException exception) {
        return ErrorResponse.builder(exception, HttpStatus.NOT_FOUND, exception.getMessage())
                .title("Entity not found")
                .type(URI.create("https://api.midominio.com/errors/not-found"))
                .property("entityType", "Note")
                .property("timestamp", Instant.now())
                .build();
    }

}
