package com.manikanta.pillreminder.Pill.Reminder.Application.service;

import com.manikanta.pillreminder.Pill.Reminder.Application.dto.DiseaseRequest;
import com.manikanta.pillreminder.Pill.Reminder.Application.dto.DiseaseResponse;
import com.manikanta.pillreminder.Pill.Reminder.Application.entities.Disease;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface DiseaseService {
   Disease addDisease(DiseaseRequest diseaseRequest);

   DiseaseResponse getDisease(Long id);

   List<DiseaseResponse> getAllDiseases();

   boolean updateDisease(DiseaseRequest diseaseRequest, Long id);

   boolean deleteDisease(Long id);
}
