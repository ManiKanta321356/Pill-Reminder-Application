package com.manikanta.pillreminder.Pill.Reminder.Application.controller;

import com.manikanta.pillreminder.Pill.Reminder.Application.dto.DiseaseRequest;
import com.manikanta.pillreminder.Pill.Reminder.Application.dto.DiseaseResponse;
import com.manikanta.pillreminder.Pill.Reminder.Application.service.DiseaseService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/disease"})
public class DiseaseController {
   private final DiseaseService diseaseService;

   public DiseaseController(DiseaseService diseaseService) {
      this.diseaseService = diseaseService;
   }

   @PostMapping({"/add"})
   public ResponseEntity<Object> addDisease(@Valid @RequestBody DiseaseRequest diseaseRequest, BindingResult result) {
      if (result.hasErrors()) {
         return new ResponseEntity(result.getAllErrors(), HttpStatus.BAD_REQUEST);
      } else {
         this.diseaseService.addDisease(diseaseRequest);
         return new ResponseEntity("Disease Created Successfully", HttpStatus.CREATED);
      }
   }

   @GetMapping({"/getall"})
   public ResponseEntity<Object> getAllDiseases() {
      List<DiseaseResponse> diseases = this.diseaseService.getAllDiseases();
      return !diseases.isEmpty() ? new ResponseEntity(diseases, HttpStatus.OK) : new ResponseEntity("Error while retrieving all diseases.", HttpStatus.INTERNAL_SERVER_ERROR);
   }

   @GetMapping({"/get/{id}"})
   public ResponseEntity<Object> getDiseaseById(@PathVariable Long id) {
      try {
         DiseaseResponse disease = this.diseaseService.getDisease(id);
         return new ResponseEntity(disease, HttpStatus.OK);
      } catch (Exception var3) {
         return new ResponseEntity("Disease not found with ID: " + id, HttpStatus.NOT_FOUND);
      }
   }

   @PutMapping({"/update/{id}"})
   public ResponseEntity<Object> updateDisease(@RequestBody DiseaseRequest diseaseRequest, @PathVariable Long id) {
      boolean flag = this.diseaseService.updateDisease(diseaseRequest, id);
      return flag ? new ResponseEntity("Disease Updated Successfully", HttpStatus.OK) : new ResponseEntity("Error while updating disease.", HttpStatus.NOT_FOUND);
   }

   @DeleteMapping({"/delete/{id}"})
   public ResponseEntity<Object> deleteDisease(@PathVariable Long id) {
      boolean flag = this.diseaseService.deleteDisease(id);
      return flag ? new ResponseEntity("Disease Deleted Successfully", HttpStatus.OK) : new ResponseEntity("Error while deleting disease.", HttpStatus.NOT_FOUND);
   }
}
