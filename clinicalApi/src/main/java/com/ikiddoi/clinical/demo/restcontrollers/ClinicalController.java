package com.ikiddoi.clinical.demo.restcontrollers;

import com.ikiddoi.clinical.demo.entities.Clinicaldata;
import com.ikiddoi.clinical.demo.entities.Patient;
import com.ikiddoi.clinical.demo.repos.ClinicalDataRepository;
import com.ikiddoi.clinical.demo.repos.PatientRepository;
import com.ikiddoi.clinical.demo.restcontrollers.dto.ClinicalDataRequest;
import com.ikiddoi.clinical.demo.util.BMICalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ClinicalController {

    private ClinicalDataRepository repository;
    private PatientRepository patientRepository;

    @Autowired
    ClinicalController(ClinicalDataRepository repository, PatientRepository patientRepository) {
        this.repository = repository;
        this.patientRepository = patientRepository;
    }

    @RequestMapping(value = "/clinicals", method = RequestMethod.POST)
    public Clinicaldata saveClinicalData(@RequestBody ClinicalDataRequest clinicalDataRequest) {
        Patient patient = patientRepository.findById(clinicalDataRequest.getPatientId()).get();
        Clinicaldata data = new Clinicaldata();
        data.setComponentName(clinicalDataRequest.getComponentName());
        data.setComponentValue(clinicalDataRequest.getComponentValue());
        data.setPatient(patient);
        return repository.save(data);
    }

    @RequestMapping(value = "/clinicals/{patientId}/{componentName}", method = RequestMethod.GET)
    public List<Clinicaldata> getClinicalData(@PathVariable("patientId") int patientId,
                                              @PathVariable("componentName") String componentName) {
        List<Clinicaldata> clinicalData = repository.findByPatientIdAndComponentNameOrderByMeasuredDateTime(patientId, componentName);

        if (componentName.equals("bmi")){
            componentName="hw";
        }

        List<Clinicaldata> duplicateClinicalData = new ArrayList<>(clinicalData);
        for (Clinicaldata eachEntry : duplicateClinicalData) {
            BMICalculator.calculateBMI(clinicalData, eachEntry);
        }
        return repository.findByPatientIdAndComponentNameOrderByMeasuredDateTime(patientId, componentName);
    }

}