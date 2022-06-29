package net.openwebinars.springboot.errorhandling.error.model.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.openwebinars.springboot.errorhandling.error.model.ApiError;
import net.openwebinars.springboot.errorhandling.error.model.ApiSubError;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class ApiErrorImpl implements ApiError {

    private HttpStatus status;
    private String message;
    private String path;

    private int statusCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    @Builder.Default
    private LocalDateTime date = LocalDateTime.now();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ApiSubError> subErrors;

    public int getStatusCode() {
        return status != null ? status.value() : 0;
    }


    public DefaultErrorAttributes toErrorAttributes() {
        return null;
    }

    public static ApiError fromErrorAttributes(Map<String, Object> defaultErrorAttributesMap) {

        int statusCode = ((Integer)defaultErrorAttributesMap.get("status")).intValue();


        ApiErrorImpl result =
                ApiErrorImpl.builder()
                    .status(HttpStatus.valueOf(statusCode))
                    .statusCode(statusCode)
                    .message((String) defaultErrorAttributesMap.getOrDefault("message", "No message available"))
                    .path((String) defaultErrorAttributesMap.getOrDefault("path", "No path available"))
                    .build();

        if (defaultErrorAttributesMap.containsKey("errors")) {

            List<ObjectError> errors = (List<ObjectError>) defaultErrorAttributesMap.get("errors");

            List<ApiSubError> subErrors = errors.stream()
                    .map(ApiValidationSubError::fromObjectError)
                    .collect(Collectors.toList());

            result.setSubErrors(subErrors);

        }

        return result;
    }


}
