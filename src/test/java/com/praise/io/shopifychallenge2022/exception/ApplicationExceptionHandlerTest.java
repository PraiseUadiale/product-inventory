package com.praise.io.shopifychallenge2022.exception;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.validation.BindException;

class ApplicationExceptionHandlerTest {

  @Test
  void testHandleInvalidInput() {
    ApplicationExceptionHandler applicationExceptionHandler = new ApplicationExceptionHandler();
    BindException ex = new BindException(
        new BindException(
            new BindException(new BindException(new BindException("Target", "Object Name")))));
    assertTrue(
        applicationExceptionHandler.handleInvalidInput(ex, new ConcurrentModel()).isReference());
  }

  @Test
  void testHandleBusinessException() {
    ApplicationExceptionHandler applicationExceptionHandler = new ApplicationExceptionHandler();
    ProductNotFoundException ex = new ProductNotFoundException("An error occurred");
    assertTrue(applicationExceptionHandler.handleBusinessException(ex, new ConcurrentModel())
        .isReference());
  }
}

