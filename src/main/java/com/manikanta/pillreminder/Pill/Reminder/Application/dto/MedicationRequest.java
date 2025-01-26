package com.manikanta.pillreminder.Pill.Reminder.Application.dto;

import jakarta.validation.constraints.NotNull;

public class MedicationRequest {
   @NotNull(
      message = "Tablet Name should not be null"
   )
   private String tabletName;
   @NotNull(
      message = "Tablet Dosage should not be null"
   )
   private String dosage;
   @NotNull(
      message = "Time should not be null"
   )
   private String scheduleTime;
   @NotNull(
      message = "DiseaseId should not be null"
   )
   private Long diseaseId;
   @NotNull(
      message = "UserId  should not be null"
   )
   private Long userId;

   public void setTabletName(String tabletName) {
      this.tabletName = tabletName;
   }

   public void setDosage(String dosage) {
      this.dosage = dosage;
   }

   public void setScheduleTime(String scheduleTime) {
      this.scheduleTime = scheduleTime;
   }

   public void setDiseaseId(Long diseaseId) {
      this.diseaseId = diseaseId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public String getTabletName() {
      return this.tabletName;
   }

   public String getDosage() {
      return this.dosage;
   }

   public String getScheduleTime() {
      return this.scheduleTime;
   }

   public Long getDiseaseId() {
      return this.diseaseId;
   }

   public Long getUserId() {
      return this.userId;
   }

   public MedicationRequest(String tabletName, String dosage, String scheduleTime, Long diseaseId, Long userId) {
      this.tabletName = tabletName;
      this.dosage = dosage;
      this.scheduleTime = scheduleTime;
      this.diseaseId = diseaseId;
      this.userId = userId;
   }
}
