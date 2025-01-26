package com.manikanta.pillreminder.Pill.Reminder.Application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserRequest {
   @NotBlank(
      message = "Name should not be blank"
   )
   private String name;
   @Email(
      message = "The email must be in the correct format."
   )
   @Pattern(
      regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
      message = "The email must be in the correct format (e.g., user@example.com)."
   )
   private String email;
   @Pattern(
      regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])[A-Za-z\\d@#$%^&+=!]{8,}$",
      message = "The password must be at least 8 characters long, including one uppercase letter, one lowercase letter, one digit, and one special character."
   )
   private String password;
   @Pattern(
      regexp = "^[6-9][0-9]{9}$",
      message = "The mobile number must be 10 digits starting with 6-9."
   )
   private String mobileNumber;

   public void setName(@NotBlank(message = "Name should not be blank") String name) {
      this.name = name;
   }

   public void setEmail(@Email(message = "The email must be in the correct format.") @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "The email must be in the correct format (e.g., user@example.com).") String email) {
      this.email = email;
   }

   public void setPassword(@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])[A-Za-z\\d@#$%^&+=!]{8,}$",message = "The password must be at least 8 characters long, including one uppercase letter, one lowercase letter, one digit, and one special character.") String password) {
      this.password = password;
   }

   public void setMobileNumber(@Pattern(regexp = "^[6-9][0-9]{9}$",message = "The mobile number must be 10 digits starting with 6-9.") String mobileNumber) {
      this.mobileNumber = mobileNumber;
   }

   @NotBlank(
      message = "Name should not be blank"
   )
   public String getName() {
      return this.name;
   }

   @Email(
      message = "The email must be in the correct format."
   )
   @Pattern(
      regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
      message = "The email must be in the correct format (e.g., user@example.com)."
   )
   public String getEmail() {
      return this.email;
   }

   @Pattern(
      regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])[A-Za-z\\d@#$%^&+=!]{8,}$",
      message = "The password must be at least 8 characters long, including one uppercase letter, one lowercase letter, one digit, and one special character."
   )
   public String getPassword() {
      return this.password;
   }

   @Pattern(
      regexp = "^[6-9][0-9]{9}$",
      message = "The mobile number must be 10 digits starting with 6-9."
   )
   public String getMobileNumber() {
      return this.mobileNumber;
   }
}
