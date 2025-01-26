package com.manikanta.pillreminder.Pill.Reminder.Application.repo;

import com.manikanta.pillreminder.Pill.Reminder.Application.entities.Medication;
import java.time.LocalTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {
   List<Medication> findByDiseaseId(Long id);

   List<Medication> findByScheduleTime(LocalTime scheduleTime);
}
