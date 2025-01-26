package com.manikanta.pillreminder.Pill.Reminder.Application.repo;

import com.manikanta.pillreminder.Pill.Reminder.Application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
