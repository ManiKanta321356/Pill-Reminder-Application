package com.manikanta.pillreminder.Pill.Reminder.Application.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(
        name = "disease"
)
public class Disease {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Long id;
    @Column(
            name = "disease_name",
            nullable = false,
            updatable = true
    )
    private String diseaseName;
    @Column(
            name = "disease_details",
            nullable = true,
            updatable = true
    )
    private String diseaseDetails;
    @OneToMany(
            mappedBy = "disease",
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @Column(
            name = "medications"
    )
    @JsonManagedReference
    private List<Medication> medications;
    @ManyToOne
    @JoinColumn(
            name = "user_id",
            updatable = false
    )
    @JsonIgnore
    private User user;

    public void setId(Long id) {
        this.id = id;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public void setDiseaseDetails(String diseaseDetails) {
        this.diseaseDetails = diseaseDetails;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return this.id;
    }

    public String getDiseaseName() {
        return this.diseaseName;
    }

    public String getDiseaseDetails() {
        return this.diseaseDetails;
    }

    public List<Medication> getMedications() {
        return this.medications;
    }

    public User getUser() {
        return this.user;
    }
}
