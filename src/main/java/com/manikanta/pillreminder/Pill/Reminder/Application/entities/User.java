package com.manikanta.pillreminder.Pill.Reminder.Application.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
        name = "user"
)
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    @Column(
            name = "name"
    )
    private String name;
    @Column(
            name = "email",
            length = 100,
            unique = true
    )
    private String email;
    @Column(
            name = "password"
    )
    private String password;
    @Column(
            name = "mobile_number",
            unique = true,
            nullable = false
    )
    private String mobileNumber;
    @Column(
            name = "created_at"
    )
    private LocalDateTime createdAt;
    @Column(
            name = "updated_at"
    )
    private LocalDateTime updatedAt;
    @OneToMany(
            mappedBy = "user",
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Medication> medications;
    @OneToMany(
            mappedBy = "user",
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Disease> diseases;

    public List<Medication> getMedications() {
        return this.medications;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getMobileNumber() {
        return this.mobileNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public List<Disease> getDiseases() {
        return this.diseases;
    }
}
