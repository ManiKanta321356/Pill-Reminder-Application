package com.manikanta.pillreminder.Pill.Reminder.Application.dto;

import jakarta.validation.constraints.NotNull;

public class DiseaseRequest {
   @NotNull(
      message = "Disease Name should not be empty"
   )
   private String diseaseName;
   @NotNull
   private String diseaseDetails;
   @NotNull(
      message = "Id should not be null"
   )
   private Long userId;

   public String getDiseaseName() {
      return this.diseaseName;
   }

   public String getDiseaseDetails() {
      return this.diseaseDetails;
   }

   public Long getUserId() {
      return this.userId;
   }

   public void setDiseaseName(String diseaseName) {
      this.diseaseName = diseaseName;
   }

   public void setDiseaseDetails(String diseaseDetails) {
      this.diseaseDetails = diseaseDetails;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }
}
