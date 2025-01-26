package com.manikanta.pillreminder.Pill.Reminder.Application.service.impl;

import com.manikanta.pillreminder.Pill.Reminder.Application.dto.MedicationRequest;
import com.manikanta.pillreminder.Pill.Reminder.Application.dto.MedicationResponse;
import com.manikanta.pillreminder.Pill.Reminder.Application.entities.Disease;
import com.manikanta.pillreminder.Pill.Reminder.Application.entities.Medication;
import com.manikanta.pillreminder.Pill.Reminder.Application.entities.User;
import com.manikanta.pillreminder.Pill.Reminder.Application.exception.ResourceNotFoundException;
import com.manikanta.pillreminder.Pill.Reminder.Application.repo.DiseaseRepository;
import com.manikanta.pillreminder.Pill.Reminder.Application.repo.MedicationRepository;
import com.manikanta.pillreminder.Pill.Reminder.Application.repo.UserRepository;
import com.manikanta.pillreminder.Pill.Reminder.Application.service.MedicationService;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicationServiceImpl implements MedicationService {
   @Autowired
   private MedicationRepository medicationRepository;
   @Autowired
   private DiseaseRepository diseaseRepository;
   @Autowired
   private UserRepository userRepository;

   public Medication addMedication(MedicationRequest medicationRequest) {
      Disease disease = (Disease)this.diseaseRepository.findById(medicationRequest.getDiseaseId()).orElseThrow(() -> {
         return new ResourceNotFoundException("Disease not found while adding medication", "DiseaseId", medicationRequest.getDiseaseId());
      });
      User user = (User)this.userRepository.findById(medicationRequest.getUserId()).orElseThrow(() -> {
         return new ResourceNotFoundException("User not found while adding medication", "UserId", medicationRequest.getUserId());
      });
      String timeInput = medicationRequest.getScheduleTime().replaceAll("\\s+", " ").trim().toUpperCase();
      System.out.println("Received timeInput: " + timeInput);
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);

      LocalTime time;
      try {
         time = LocalTime.parse(timeInput, formatter);
         System.out.println("Parsed LocalTime: " + time);
      } catch (DateTimeParseException var9) {
         throw new IllegalArgumentException("Invalid time format. Expected format: hh:mm a (e.g., 10:00 AM)", var9);
      }

      Medication medication = new Medication();
      medication.setTabletName(medicationRequest.getTabletName());
      medication.setDosage(medicationRequest.getDosage());
      medication.setScheduleTime(time);
      medication.setDisease(disease);
      medication.setUser(user);
      medication.setCreatedAt(LocalDateTime.now());
      medication.setUpdatedAt(LocalDateTime.now());
      Medication savedMedication = (Medication)this.medicationRepository.save(medication);
      return savedMedication;
   }

   public MedicationResponse getMedication(Long id) {
      Optional<Medication> medication = this.medicationRepository.findById(id);
      return (MedicationResponse)medication.map(this::convertToResponse).orElseThrow(() -> {
         return new ResourceNotFoundException("Medication not found", "MedicationId", id);
      });
   }

   public List<MedicationResponse> getAllMedications() {
      List<Medication> medications = this.medicationRepository.findAll();
      return (List)medications.stream().map(this::convertToResponse).collect(Collectors.toList());
   }

   public boolean updateMedication(MedicationRequest medicationRequest, Long id) {
      Medication medication = (Medication)this.medicationRepository.findById(id).orElseThrow(() -> {
         return new ResourceNotFoundException("Medication not found while Updating medication", "MedicationId", id);
      });
      Disease disease = (Disease)this.diseaseRepository.findById(medicationRequest.getDiseaseId()).orElseThrow(() -> {
         return new ResourceNotFoundException("Disease not found while Updating medication", "DiseaseId", medicationRequest.getDiseaseId());
      });
      User user = (User)this.userRepository.findById(medicationRequest.getUserId()).orElseThrow(() -> {
         return new ResourceNotFoundException("User not found while Updating medication", "UserId", medicationRequest.getUserId());
      });
      String timeInput = medicationRequest.getScheduleTime();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
      LocalTime time = LocalTime.parse(timeInput, formatter);
      medication.setTabletName(medicationRequest.getTabletName());
      medication.setDosage(medicationRequest.getDosage());
      medication.setScheduleTime(time);
      medication.setDisease(disease);
      medication.setUser(user);
      medication.setCreatedAt(medication.getCreatedAt());
      medication.setUpdatedAt(LocalDateTime.now());
      this.medicationRepository.save(medication);
      return true;
   }

   public boolean deleteMedication(Long id) {
      Medication medication = (Medication)this.medicationRepository.findById(id).orElseThrow(() -> {
         return new ResourceNotFoundException("Medication not found while deleting medication", "MedicationId", id);
      });
      this.medicationRepository.delete(medication);
      return true;
   }

   private MedicationResponse convertToResponse(Medication medication) {
      MedicationResponse medicationResponse = new MedicationResponse();
      medicationResponse.setId(medication.getId());
      medicationResponse.setDosage(medication.getDosage());
      medicationResponse.setDiseaseId(medication.getDisease().getId());
      medicationResponse.setUserId(medication.getUser().getId());
      medicationResponse.setTabletName(medication.getTabletName());
      return medicationResponse;
   }
}
