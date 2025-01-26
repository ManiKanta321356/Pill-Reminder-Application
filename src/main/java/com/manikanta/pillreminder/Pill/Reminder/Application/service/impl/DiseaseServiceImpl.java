package com.manikanta.pillreminder.Pill.Reminder.Application.service.impl;

import com.manikanta.pillreminder.Pill.Reminder.Application.dto.DiseaseRequest;
import com.manikanta.pillreminder.Pill.Reminder.Application.dto.DiseaseResponse;
import com.manikanta.pillreminder.Pill.Reminder.Application.dto.DiseaseResponseForUser;
import com.manikanta.pillreminder.Pill.Reminder.Application.dto.MedicationResponseForDisease;
import com.manikanta.pillreminder.Pill.Reminder.Application.entities.Disease;
import com.manikanta.pillreminder.Pill.Reminder.Application.entities.Medication;
import com.manikanta.pillreminder.Pill.Reminder.Application.entities.User;
import com.manikanta.pillreminder.Pill.Reminder.Application.exception.ResourceNotFoundException;
import com.manikanta.pillreminder.Pill.Reminder.Application.repo.DiseaseRepository;
import com.manikanta.pillreminder.Pill.Reminder.Application.repo.MedicationRepository;
import com.manikanta.pillreminder.Pill.Reminder.Application.repo.UserRepository;
import com.manikanta.pillreminder.Pill.Reminder.Application.service.DiseaseService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiseaseServiceImpl implements DiseaseService {
   @Autowired
   private DiseaseRepository diseaseRepository;
   @Autowired
   private UserRepository userRepository;
   @Autowired
   private MedicationRepository medicationRepository;

   public Disease addDisease(DiseaseRequest diseaseRequest) {
      Disease disease = new Disease();
      User user = (User)this.userRepository.findById(diseaseRequest.getUserId()).orElseThrow(() -> {
         return new ResourceNotFoundException("User not found while adding disease", "UserId", diseaseRequest.getUserId());
      });
      disease.setDiseaseName(diseaseRequest.getDiseaseName());
      disease.setDiseaseDetails(diseaseRequest.getDiseaseDetails());
      disease.setUser(user);
      this.diseaseRepository.save(disease);
      return disease;
   }

   public DiseaseResponse getDisease(Long id) {
      Disease disease = (Disease)this.diseaseRepository.findById(id).orElseThrow(() -> {
         return new ResourceNotFoundException("Disease", "DiseaseId", id);
      });
      return this.mapToDiseaseResponse(disease);
   }

   public List<DiseaseResponse> getAllDiseases() {
      List<Disease> diseases = this.diseaseRepository.findAll();
      List<DiseaseResponse> diseaseResponses = new ArrayList();
      Iterator var3 = diseases.iterator();

      while(var3.hasNext()) {
         Disease d = (Disease)var3.next();
         DiseaseResponse diseaseResponse = this.mapToDiseaseResponse(d);
         diseaseResponses.add(diseaseResponse);
      }

      return diseaseResponses;
   }

   public boolean updateDisease(DiseaseRequest diseaseRequest, Long id) {
      Disease disease = (Disease)this.diseaseRepository.findById(id).orElseThrow(() -> {
         return new ResourceNotFoundException("Disease", "diseaseId", id);
      });
      User user = (User)this.userRepository.findById(diseaseRequest.getUserId()).orElseThrow(() -> {
         return new ResourceNotFoundException("User not found while adding disease", "UserId", diseaseRequest.getUserId());
      });
      System.out.println(disease);
      disease.setDiseaseName(diseaseRequest.getDiseaseName());
      disease.setDiseaseDetails(diseaseRequest.getDiseaseDetails());
      disease.setUser(user);
      this.diseaseRepository.save(disease);
      return true;
   }

   public boolean deleteDisease(Long id) {
      Disease var10000 = (Disease)this.diseaseRepository.findById(id).orElseThrow(() -> {
         return new ResourceNotFoundException("Disease", "diseaseId", id);
      });
      this.diseaseRepository.deleteById(id);
      return true;
   }

   public DiseaseResponse mapToDiseaseResponse(Disease disease) {
      List<Medication> list = this.medicationRepository.findByDiseaseId(disease.getId());
      List<MedicationResponseForDisease> list1 = new ArrayList();
      Iterator var4 = list.iterator();

      while(var4.hasNext()) {
         Medication m = (Medication)var4.next();
         MedicationResponseForDisease medicationResponseForDisease = new MedicationResponseForDisease();
         medicationResponseForDisease.setTabletName(m.getTabletName());
         medicationResponseForDisease.setDosage(m.getDosage());
         medicationResponseForDisease.setScheduleTime(m.getScheduleTime());
         list1.add(medicationResponseForDisease);
      }

      DiseaseResponse diseaseResponse = new DiseaseResponse();
      diseaseResponse.setId(disease.getId());
      diseaseResponse.setDiseaseName(disease.getDiseaseName());
      diseaseResponse.setDiseaseDetails(disease.getDiseaseDetails());
      diseaseResponse.setUserId(disease.getUser().getId());
      diseaseResponse.setMedicationResponses(list1);
      return diseaseResponse;
   }

   public DiseaseResponseForUser mapToDiseaseResponseForUser(Disease disease) {
      List<Medication> list = this.medicationRepository.findByDiseaseId(disease.getId());
      List<MedicationResponseForDisease> list1 = new ArrayList();
      Iterator var4 = list.iterator();

      while(var4.hasNext()) {
         Medication m = (Medication)var4.next();
         MedicationResponseForDisease medicationResponseForDisease = new MedicationResponseForDisease();
         medicationResponseForDisease.setTabletName(m.getTabletName());
         medicationResponseForDisease.setDosage(m.getDosage());
         medicationResponseForDisease.setScheduleTime(m.getScheduleTime());
         list1.add(medicationResponseForDisease);
      }

      DiseaseResponseForUser diseaseResponseForUser = new DiseaseResponseForUser();
      diseaseResponseForUser.setDiseaseName(disease.getDiseaseName());
      diseaseResponseForUser.setDiseaseDetails(disease.getDiseaseDetails());
      diseaseResponseForUser.setMedicationResponses(list1);
      return diseaseResponseForUser;
   }
}
