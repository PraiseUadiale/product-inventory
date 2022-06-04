package com.praise.io.shopifychallenge2022.exception;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@RestControllerAdvice
public class ApplicationExceptionHandler {

  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = BindException.class)
  public ModelAndView handleInvalidInput(BindException ex, Model model) {
    Map<String, String> errorMap = new HashMap<>();
    ex.getBindingResult()
        .getFieldErrors()
        .forEach(
            error -> {
              errorMap.put(error.getField(), error.getDefaultMessage());
            });
    ModelAndView modelAndView = new ModelAndView();
    model.addAttribute("errorMessage", errorMap);
    modelAndView.setViewName("error");
    return modelAndView;
  }

  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(value = ProductNotFoundException.class)
  public ModelAndView handleBusinessException(ProductNotFoundException ex, Model model) {

    Map<String, String> errorMap = Map.of("errorMessage", ex.getMessage());
    ModelAndView modelAndView = new ModelAndView();
    model.addAttribute("errorMessage", errorMap);
    modelAndView.setViewName("error");
    return modelAndView;
  }
}
