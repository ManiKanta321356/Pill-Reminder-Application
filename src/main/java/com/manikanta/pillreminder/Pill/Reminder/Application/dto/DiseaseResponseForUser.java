package com.manikanta.pillreminder.Pill.Reminder.Application.dto;

import java.util.List;

public class DiseaseResponseForUser {
   private String diseaseName;
   private String diseaseDetails;
   List<MedicationResponseForDisease> medicationResponses;

   public void setDiseaseName(String diseaseName) {
      this.diseaseName = diseaseName;
   }

   public void setDiseaseDetails(String diseaseDetails) {
      this.diseaseDetails = diseaseDetails;
   }

   public void setMedicationResponses(List<MedicationResponseForDisease> medicationResponses) {
      this.medicationResponses = medicationResponses;
   }

   public String getDiseaseName() {
      return this.diseaseName;
   }

   public String getDiseaseDetails() {
      return this.diseaseDetails;
   }

   public List<MedicationResponseForDisease> getMedicationResponses() {
      return this.medicationResponses;
   }
}
