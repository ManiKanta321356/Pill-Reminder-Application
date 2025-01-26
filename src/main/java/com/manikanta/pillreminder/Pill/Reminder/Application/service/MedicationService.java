package com.manikanta.pillreminder.Pill.Reminder.Application.service;

import com.manikanta.pillreminder.Pill.Reminder.Application.dto.MedicationRequest;
import com.manikanta.pillreminder.Pill.Reminder.Application.dto.MedicationResponse;
import com.manikanta.pillreminder.Pill.Reminder.Application.entities.Medication;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface MedicationService {
   Medication addMedication(MedicationRequest medicationRequest);

   MedicationResponse getMedication(Long id);

   List<MedicationResponse> getAllMedications();

   boolean deleteMedication(Long id);

   boolean updateMedication(MedicationRequest medicationRequest, Long id);
}
