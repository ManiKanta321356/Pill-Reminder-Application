package com.manikanta.pillreminder.Pill.Reminder.Application.controller;

import com.manikanta.pillreminder.Pill.Reminder.Application.dto.MedicationRequest;
import com.manikanta.pillreminder.Pill.Reminder.Application.dto.MedicationResponse;
import com.manikanta.pillreminder.Pill.Reminder.Application.entities.Medication;
import com.manikanta.pillreminder.Pill.Reminder.Application.service.MedicationService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/medication"})
public class MedicationController {
   @Autowired
   private MedicationService medicationService;

   @PostMapping({"/add"})
   public ResponseEntity<Object> addMedication(@Valid @RequestBody MedicationRequest medicationRequest, BindingResult result) {
      if (result.hasErrors()) {
         return new ResponseEntity(result.getAllErrors(), HttpStatus.BAD_REQUEST);
      } else {
         Medication medicationResponse = this.medicationService.addMedication(medicationRequest);
         return new ResponseEntity(medicationResponse, HttpStatus.CREATED);
      }
   }

   @GetMapping({"/get/{id}"})
   public ResponseEntity<Object> getMedicationById(@PathVariable Long id) {
      MedicationResponse medicationResponse = this.medicationService.getMedication(id);
      return medicationResponse != null ? new ResponseEntity(medicationResponse, HttpStatus.OK) : new ResponseEntity("Medication not found", HttpStatus.NOT_FOUND);
   }

   @GetMapping({"/getall"})
   public ResponseEntity<Object> getAllMedications() {
      List<MedicationResponse> medications = this.medicationService.getAllMedications();
      return !medications.isEmpty() ? new ResponseEntity(medications, HttpStatus.OK) : new ResponseEntity("No medications found", HttpStatus.NO_CONTENT);
   }

   @PutMapping({"/update/{id}"})
   public ResponseEntity<Object> updateMedication(@Valid @RequestBody MedicationRequest medicationRequest, @PathVariable Long id) {
      boolean isUpdated = this.medicationService.updateMedication(medicationRequest, id);
      return isUpdated ? new ResponseEntity("Medication updated successfully", HttpStatus.OK) : new ResponseEntity("Medication not found", HttpStatus.NOT_FOUND);
   }

   @DeleteMapping({"/delete/{id}"})
   public ResponseEntity<Object> deleteMedication(@PathVariable Long id) {
      boolean isDeleted = this.medicationService.deleteMedication(id);
      return isDeleted ? new ResponseEntity("Medication deleted successfully", HttpStatus.OK) : new ResponseEntity("Medication not found", HttpStatus.NOT_FOUND);
   }
}
