package com.manikanta.pillreminder.Pill.Reminder.Application.scheduler;

import com.manikanta.pillreminder.Pill.Reminder.Application.entities.Medication;
import com.manikanta.pillreminder.Pill.Reminder.Application.repo.MedicationRepository;
import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MedicationReminderScheduler {
   private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
   @Autowired
   private MedicationRepository medicationRepository;
   @Autowired
   private JavaMailSender mailSender;

   @PostConstruct
   public void initializeScheduler() {
      this.scheduleNextRun();
   }

   private void scheduleNextRun() {
      Runnable task = this::sendMedicationReminders;
      long initialDelay = this.computeInitialDelay();
      System.out.println("Scheduler will start at the next 30-minute interval in " + initialDelay + " seconds.");
      long interval = TimeUnit.MINUTES.toSeconds(30L);
      this.scheduler.scheduleAtFixedRate(task, initialDelay, interval, TimeUnit.SECONDS);
   }

   private long computeInitialDelay() {
      LocalDateTime now = LocalDateTime.now();
      LocalDateTime nextRun;
      if (now.getMinute() < 30) {
         nextRun = now.withMinute(30).withSecond(0).withNano(0);
      } else {
         nextRun = now.plusHours(1L).withMinute(0).withSecond(0).withNano(0);
      }

      return ChronoUnit.SECONDS.between(now, nextRun);
   }

   private void sendMedicationReminders() {
      LocalTime currentTime = LocalTime.now().plusMinutes(1L).truncatedTo(ChronoUnit.MINUTES);
      System.out.println("Running email reminder task at: " + currentTime);
      List<Medication> medications = this.medicationRepository.findByScheduleTime(currentTime);
      if (medications.isEmpty()) {
         System.out.println("No medications found for the current time: " + currentTime);
      } else {
         Iterator var3 = medications.iterator();

         while(var3.hasNext()) {
            Medication medication = (Medication)var3.next();
            this.sendEmail(medication);
         }

      }
   }

   private void sendEmail(Medication medication) {
      try {
         SimpleMailMessage message = new SimpleMailMessage();
         message.setTo(medication.getUser().getEmail());
         message.setSubject("Medication Reminder");
         String var10001 = medication.getUser().getName();
         message.setText("Hello " + var10001 + ",\n\nThis is a reminder to take your medication:\nTablet Name: " + medication.getTabletName() + "\nDosage: " + medication.getDosage() + "\nScheduled Time: " + medication.getScheduleTime() + "\n\nStay healthy!");
         this.mailSender.send(message);
         System.out.println("Email sent to: " + medication.getUser().getEmail());
      } catch (Exception var3) {
         System.err.println("Failed to send email to: " + medication.getUser().getEmail());
         var3.printStackTrace();
      }

   }
}
