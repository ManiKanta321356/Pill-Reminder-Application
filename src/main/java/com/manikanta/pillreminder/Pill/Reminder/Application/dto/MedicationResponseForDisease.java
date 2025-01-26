package com.manikanta.pillreminder.Pill.Reminder.Application.dto;

import java.time.LocalTime;

public class MedicationResponseForDisease {
   private String tabletName;
   private String dosage;
   private LocalTime scheduleTime;

   public String getTabletName() {
      return this.tabletName;
   }

   public String getDosage() {
      return this.dosage;
   }

   public LocalTime getScheduleTime() {
      return this.scheduleTime;
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
}
