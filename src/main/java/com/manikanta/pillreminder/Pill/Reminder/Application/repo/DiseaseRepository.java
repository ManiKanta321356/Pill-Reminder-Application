package com.manikanta.pillreminder.Pill.Reminder.Application.repo;

import com.manikanta.pillreminder.Pill.Reminder.Application.entities.Disease;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {
   List<Disease> findByUserId(Long userId);
}
