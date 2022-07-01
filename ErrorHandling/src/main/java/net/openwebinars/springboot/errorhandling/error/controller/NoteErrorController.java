package net.openwebinars.springboot.errorhandling.error.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import net.openwebinars.springboot.errorhandling.error.model.ApiError;
import net.openwebinars.springboot.errorhandling.error.model.impl.ApiErrorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(NoteErrorController.ERROR_PATH)
public class NoteErrorController extends AbstractErrorController {


    private final ObjectMapper objectMapper;

    static final String ERROR_PATH = "/error";


    public NoteErrorController(ErrorAttributes errorAttributes, ObjectMapper objectMapper) {
        // De esta forma, no le permitimos añadir ningún ViewResolver que pueda hacer renderizar una página HTML.
        super(errorAttributes, Collections.emptyList());
        this.objectMapper = objectMapper;
    }

    @RequestMapping
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> mapErrorAttributes = this.getErrorAttributes(request,
                ErrorAttributeOptions.of(ErrorAttributeOptions.Include.BINDING_ERRORS, ErrorAttributeOptions.Include.EXCEPTION, ErrorAttributeOptions.Include.MESSAGE, ErrorAttributeOptions.Include.STACK_TRACE));
        HttpStatus status = this.getStatus(request);
        ApiError apiError = ApiError.fromErrorAttributes(mapErrorAttributes);
        return ResponseEntity.status(status).body(objectMapper.convertValue(apiError, Map.class));
     }

}
