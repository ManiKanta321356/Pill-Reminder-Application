package com.manikanta.pillreminder.Pill.Reminder.Application.controller;

import com.manikanta.pillreminder.Pill.Reminder.Application.dto.DiseaseResponseForUser;
import com.manikanta.pillreminder.Pill.Reminder.Application.dto.UserRequest;
import com.manikanta.pillreminder.Pill.Reminder.Application.dto.UserResponse;
import com.manikanta.pillreminder.Pill.Reminder.Application.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/user"})
public class UserController {
   @Autowired
   private UserService userService;

   @PostMapping({"/add"})
   public ResponseEntity<Object> createUser(@Valid @RequestBody UserRequest userRequest, BindingResult result) {
      if (result.hasErrors()) {
         return new ResponseEntity(result.getAllErrors(), HttpStatus.BAD_REQUEST);
      } else {
         this.userService.createUser(userRequest);
         return new ResponseEntity("User Created Successfully", HttpStatus.CREATED);
      }
   }

   @GetMapping({"/getall"})
   public ResponseEntity<Object> getAllUsers() {
      List<UserResponse> users = this.userService.getAllUsers();
      return !users.isEmpty() ? new ResponseEntity(users, HttpStatus.OK) : new ResponseEntity("There is an Error while retrieving all the users.", HttpStatus.INTERNAL_SERVER_ERROR);
   }

   @GetMapping({"/get/{id}"})
   public ResponseEntity<Object> getUserById(@PathVariable Long id) {
      UserResponse user = this.userService.getUser(id);
      if (!user.equals((Object)null)) {
         return new ResponseEntity(user, HttpStatus.OK);
      } else {
         return user.equals((Object)null) ? new ResponseEntity("There is no such user.", HttpStatus.NOT_FOUND) : new ResponseEntity("An error occurred while getting the user", HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   @PutMapping({"/update/{id}"})
   public ResponseEntity<Object> updateUser(@RequestBody UserRequest userRequest, @PathVariable Long id) {
      boolean flag = this.userService.updateUser(userRequest, id);
      return flag ? new ResponseEntity("User Updated Successfully", HttpStatus.OK) : new ResponseEntity("User Not updated Successfully", HttpStatus.NOT_FOUND);
   }

   @DeleteMapping({"/delete/{id}"})
   public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
      boolean flag = this.userService.deleteUser(id);
      return flag ? new ResponseEntity("User Deleted Successfully", HttpStatus.OK) : new ResponseEntity("User Deleted Successfully", HttpStatus.NOT_FOUND);
   }

   @GetMapping({"/get/userdiseases/{id}"})
   public ResponseEntity<Object> getDiseasesOfUser(@PathVariable Long id) {
      List<DiseaseResponseForUser> list = this.userService.getDiseasesOfUser(id);
      return !list.isEmpty() ? new ResponseEntity(list, HttpStatus.OK) : new ResponseEntity("There is an Error while retrieving all the users.", HttpStatus.INTERNAL_SERVER_ERROR);
   }
}
