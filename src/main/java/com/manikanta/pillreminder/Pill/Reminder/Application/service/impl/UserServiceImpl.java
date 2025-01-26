package com.manikanta.pillreminder.Pill.Reminder.Application.service.impl;

import com.manikanta.pillreminder.Pill.Reminder.Application.dto.DiseaseResponse;
import com.manikanta.pillreminder.Pill.Reminder.Application.dto.DiseaseResponseForUser;
import com.manikanta.pillreminder.Pill.Reminder.Application.dto.UserRequest;
import com.manikanta.pillreminder.Pill.Reminder.Application.dto.UserResponse;
import com.manikanta.pillreminder.Pill.Reminder.Application.entities.Disease;
import com.manikanta.pillreminder.Pill.Reminder.Application.entities.User;
import com.manikanta.pillreminder.Pill.Reminder.Application.exception.ResourceNotFoundException;
import com.manikanta.pillreminder.Pill.Reminder.Application.repo.DiseaseRepository;
import com.manikanta.pillreminder.Pill.Reminder.Application.repo.UserRepository;
import com.manikanta.pillreminder.Pill.Reminder.Application.service.UserService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
   @Autowired
   private UserRepository userRepository;
   @Autowired
   private DiseaseRepository diseaseRepository;
   @Autowired
   private DiseaseServiceImpl diseaseServiceImpl;

   public User createUser(UserRequest userRequest) {
      User user = new User();
      user.setName(userRequest.getName());
      user.setEmail(userRequest.getEmail());
      user.setMobileNumber(userRequest.getMobileNumber());
      user.setPassword(userRequest.getPassword());
      LocalDateTime now = LocalDateTime.now();
      user.setCreatedAt(now);
      user.setUpdatedAt(now);
      return (User)this.userRepository.save(user);
   }

   public UserResponse getUser(Long id) {
      User user = (User)this.userRepository.findById(id).orElseThrow(() -> {
         return new ResourceNotFoundException("User", "UserId", id);
      });
      return this.mapToUserResponse(user);
   }

   public List<UserResponse> getAllUsers() {
      List<User> users = this.userRepository.findAll();
      return (List)users.stream().map(this::mapToUserResponse).collect(Collectors.toList());
   }

   public boolean updateUser(UserRequest userRequest, Long id) {
      User user = (User)this.userRepository.findById(id).orElseThrow(() -> {
         return new ResourceNotFoundException("User", "UserId", id);
      });
      user.setName(userRequest.getName());
      user.setEmail(userRequest.getEmail());
      user.setPassword(userRequest.getPassword());
      user.setMobileNumber(userRequest.getMobileNumber());
      user.setUpdatedAt(LocalDateTime.now());
      this.userRepository.save(user);
      return true;
   }

   public boolean deleteUser(Long id) {
      User user = (User)this.userRepository.findById(id).orElseThrow(() -> {
         return new ResourceNotFoundException("User", "UserId", id);
      });
      this.userRepository.delete(user);
      return true;
   }

   public List<DiseaseResponseForUser> getDiseasesOfUser(Long id) {
      List<Disease> diseaseList = this.diseaseRepository.findByUserId(id);
      List<DiseaseResponseForUser> list = new ArrayList();
      Iterator var4 = diseaseList.iterator();

      while(var4.hasNext()) {
         Disease d = (Disease)var4.next();
         new DiseaseResponseForUser();
         DiseaseResponseForUser diseaseResponseForUser = this.diseaseServiceImpl.mapToDiseaseResponseForUser(d);
         list.add(diseaseResponseForUser);
      }

      return list;
   }

   private UserResponse mapToUserResponse(User user) {
      List<Disease> diseaseList = this.diseaseRepository.findByUserId(user.getId());
      List<DiseaseResponse> list = new ArrayList();
      Iterator var4 = diseaseList.iterator();

      while(var4.hasNext()) {
         Disease d = (Disease)var4.next();
         new DiseaseResponse();
         DiseaseResponse diseaseResponse = this.diseaseServiceImpl.mapToDiseaseResponse(d);
         list.add(diseaseResponse);
      }

      List<DiseaseResponseForUser> list1 = new ArrayList();
      Iterator var9 = diseaseList.iterator();

      while(var9.hasNext()) {
         Disease d = (Disease)var9.next();
         new DiseaseResponseForUser();
         DiseaseResponseForUser diseaseResponseForUser = this.diseaseServiceImpl.mapToDiseaseResponseForUser(d);
         list1.add(diseaseResponseForUser);
      }

      UserResponse userResponse = new UserResponse();
      userResponse.setId(user.getId());
      userResponse.setName(user.getName());
      userResponse.setEmail(user.getEmail());
      userResponse.setMobileNumber(user.getMobileNumber());
      userResponse.setPassword(user.getPassword());
      userResponse.setDiseaseResponseForUsers(list1);
      return userResponse;
   }
}
