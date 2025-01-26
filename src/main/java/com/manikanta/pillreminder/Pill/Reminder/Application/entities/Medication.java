package com.manikanta.pillreminder.Pill.Reminder.Application.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(
        name = "medication"
)
public class Medication {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    @Column(
            name = "tablet_name",
            nullable = false,
            updatable = true
    )
    private String tabletName;
    @Column(
            name = "dosage",
            nullable = false,
            updatable = true
    )
    private String dosage;
    @Column(
            name = "schedule_time",
            nullable = false,
            updatable = true
    )
    private LocalTime scheduleTime;
    @ManyToOne
    @JoinColumn(
            name = "disease_id"
    )
    @JsonIgnore
    private Disease disease;
    @ManyToOne
    @JoinColumn(
            name = "user_id"
    )
    @JsonIgnore
    private User user;
    @Column(
            name = "created_at"
    )
    private LocalDateTime createdAt;
    @Column(
            name = "updated_at"
    )
    private LocalDateTime updatedAt;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTabletName(String tabletName) {
        this.tabletName = tabletName;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setScheduleTime(LocalTime scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return this.id;
    }

    public String getTabletName() {
        return this.tabletName;
    }

    public String getDosage() {
        return this.dosage;
    }

    public LocalTime getScheduleTime() {
        return this.scheduleTime;
    }

    public Disease getDisease() {
        return this.disease;
    }

    public User getUser() {
        return this.user;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }
}
