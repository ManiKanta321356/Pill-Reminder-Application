package com.manikanta.pillreminder.Pill.Reminder.Application.dto;

import com.manikanta.pillreminder.Pill.Reminder.Application.entities.Medication;
import java.util.List;

public class DiseaseResponse {
   private Long id;
   private String diseaseName;
   private String diseaseDetails;
   private List<Medication> medications;
   List<MedicationResponseForDisease> medicationResponses;
   private Long userId;

   public void setId(Long id) {
      this.id = id;
   }

   public void setDiseaseName(String diseaseName) {
      this.diseaseName = diseaseName;
   }

   public void setDiseaseDetails(String diseaseDetails) {
      this.diseaseDetails = diseaseDetails;
   }

   public void setMedications(List<Medication> medications) {
      this.medications = medications;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public Long getId() {
      return this.id;
   }

   public String getDiseaseName() {
      return this.diseaseName;
   }

   public String getDiseaseDetails() {
      return this.diseaseDetails;
   }

   public List<Medication> getMedications() {
      return this.medications;
   }

   public Long getUserId() {
      return this.userId;
   }

   public List<MedicationResponseForDisease> getMedicationResponses() {
      return this.medicationResponses;
   }

   public void setMedicationResponses(List<MedicationResponseForDisease> medicationResponses) {
      this.medicationResponses = medicationResponses;
   }
}
