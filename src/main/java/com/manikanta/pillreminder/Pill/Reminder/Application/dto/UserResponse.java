package com.manikanta.pillreminder.Pill.Reminder.Application.dto;

import com.manikanta.pillreminder.Pill.Reminder.Application.entities.Disease;
import com.manikanta.pillreminder.Pill.Reminder.Application.entities.Medication;
import java.util.List;

public class UserResponse {
   private Long id;
   private String name;
   private String email;
   private String password;
   private String mobileNumber;
   private List<Medication> medications;
   private List<DiseaseResponse> diseaseResponses;
   private List<DiseaseResponseForUser> diseaseResponseForUsers;
   private List<Disease> diseases;

   public void setId(Long id) {
      this.id = id;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public void setMobileNumber(String mobileNumber) {
      this.mobileNumber = mobileNumber;
   }

   public void setMedications(List<Medication> medications) {
      this.medications = medications;
   }

   public void setDiseases(List<Disease> diseases) {
      this.diseases = diseases;
   }

   public void setDiseaseResponses(List<DiseaseResponse> diseaseResponses) {
      this.diseaseResponses = diseaseResponses;
   }

   public List<DiseaseResponse> getDiseaseResponses() {
      return this.diseaseResponses;
   }

   public Long getId() {
      return this.id;
   }

   public String getName() {
      return this.name;
   }

   public String getEmail() {
      return this.email;
   }

   public String getPassword() {
      return this.password;
   }

   public String getMobileNumber() {
      return this.mobileNumber;
   }

   public List<Medication> getMedications() {
      return this.medications;
   }

   public List<Disease> getDiseases() {
      return this.diseases;
   }

   public void setDiseaseResponseForUsers(List<DiseaseResponseForUser> diseaseResponseForUsers) {
      this.diseaseResponseForUsers = diseaseResponseForUsers;
   }

   public List<DiseaseResponseForUser> getDiseaseResponseForUsers() {
      return this.diseaseResponseForUsers;
   }
}
