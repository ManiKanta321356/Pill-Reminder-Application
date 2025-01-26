package com.manikanta.pillreminder.Pill.Reminder.Application.exception;

import com.manikanta.pillreminder.Pill.Reminder.Application.dto.ErrorResponseDto;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
   @ExceptionHandler({ResourceNotFoundException.class})
   public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
      ErrorResponseDto errorResponseDTO = new ErrorResponseDto(webRequest.getDescription(false), HttpStatus.NOT_FOUND, exception.getMessage(), LocalDateTime.now());
      return new ResponseEntity(errorResponseDTO, HttpStatus.NOT_FOUND);
   }
}
