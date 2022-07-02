package com.praise.io.productinventory.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@RestControllerAdvice
public class ApplicationExceptionHandler {

  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = BindException.class)
  public Map<String, String> handleInvalidRequest(BindException ex) {
    Map<String, String> errorMap = new HashMap<>();

    ex.getBindingResult()
        .getFieldErrors()
        .forEach(
            error -> {
              errorMap.put(error.getField(), error.getDefaultMessage());
            });
    return errorMap;
  }

  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(value = ProductNotFoundException.class)
  public Map<String, String> handleProductNotFoundException(ProductNotFoundException ex) {

    return Map.of("errorMessage", ex.getMessage());
  }
}
