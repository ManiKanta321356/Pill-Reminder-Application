package com.manikanta.pillreminder.Pill.Reminder.Application.exception;

public class ResourceNotFoundException extends RuntimeException {
   public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
      super(String.format("%s not found with the given input data %s : '%s'", resourceName, fieldName, fieldValue));
   }
}
