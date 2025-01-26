package com.manikanta.pillreminder.Pill.Reminder.Application.dto;

import java.time.LocalTime;

public class MedicationResponse {
   private Long id;
   private String tabletName;
   private String dosage;
   private LocalTime scheduleTime;
   private Long diseaseId;
   private Long userId;

   public void setId(Long id) {
      this.id = id;
   }

   public void setTabletName(String tabletName) {
      this.tabletName = tabletName;
   }

   public void setDosage(String dosage) {
      this.dosage = dosage;
   }

   public void setScheduleTime(LocalTime scheduleTime) {
      this.scheduleTime = scheduleTime;
   }

   public void setDiseaseId(Long diseaseId) {
      this.diseaseId = diseaseId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public Long getId() {
      return this.id;
   }

   public String getTabletName() {
      return this.tabletName;
   }

   public String getDosage() {
      return this.dosage;
   }

   public LocalTime getScheduleTime() {
      return this.scheduleTime;
   }

   public Long getDiseaseId() {
      return this.diseaseId;
   }

   public Long getUserId() {
      return this.userId;
   }
}
