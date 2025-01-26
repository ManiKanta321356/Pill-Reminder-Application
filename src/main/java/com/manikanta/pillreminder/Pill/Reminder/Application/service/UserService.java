package com.manikanta.pillreminder.Pill.Reminder.Application.service;

import com.manikanta.pillreminder.Pill.Reminder.Application.dto.DiseaseResponseForUser;
import com.manikanta.pillreminder.Pill.Reminder.Application.dto.UserRequest;
import com.manikanta.pillreminder.Pill.Reminder.Application.dto.UserResponse;
import com.manikanta.pillreminder.Pill.Reminder.Application.entities.User;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
   User createUser(UserRequest userRequest);

   UserResponse getUser(Long id);

   List<UserResponse> getAllUsers();

   boolean updateUser(UserRequest userRequest, Long id);

   boolean deleteUser(Long id);

   List<DiseaseResponseForUser> getDiseasesOfUser(Long id);
}
