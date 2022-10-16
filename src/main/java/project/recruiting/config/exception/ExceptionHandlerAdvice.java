package project.recruiting.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
       return ErrorResponse.of(e.getBindingResult());
    }

    @ExceptionHandler
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException e) {
        return ErrorResponse.of(e.getConstraintViolations());
    }

    @ExceptionHandler
    @ResponseStatus(METHOD_NOT_ALLOWED)
    public ErrorResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return ErrorResponse.of(METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ErrorResponse.of(BAD_REQUEST, "Required request body is missing");
    }

    @ExceptionHandler
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {

        return ErrorResponse.of(BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity handleBusinessLogicException(BusinessException e) {
        final ErrorResponse response = ErrorResponse.of(e.getErrorCode());

        return new ResponseEntity<>(response, valueOf(e.getErrorCode().getStatus()));
    }
}
