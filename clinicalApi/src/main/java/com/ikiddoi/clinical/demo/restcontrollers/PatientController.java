package com.ikiddoi.clinical.demo.restcontrollers;

import com.ikiddoi.clinical.demo.repos.PatientRepository;
import com.ikiddoi.clinical.demo.util.BMICalculator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ikiddoi.clinical.demo.entities.Patient;
import com.ikiddoi.clinical.demo.entities.Clinicaldata;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PatientController {

    private PatientRepository patientRepository;
    Map<String,String> filters = new HashMap<>();
    @Autowired
    PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @RequestMapping(value = "/patients", method = RequestMethod.POST)
    public Patient savePatient(@RequestBody Patient patient) {
        System.out.println(patient.getFirstName());
        return patientRepository.save(patient);
    }

    @RequestMapping(value = "/patients/{id}", method = RequestMethod.GET)
    public Patient getPatient(@PathVariable("id") int id) {
        return patientRepository.findById(id).get();
    }

    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    @RequestMapping(value = "/patients/analyze/{id}", method = RequestMethod.GET)
    public Patient analyse(@PathVariable("id") int id) {
        Patient patient = patientRepository.findById(id).get();
        List<Clinicaldata> clinicalData = (patient.getClinicalData());
        for (Clinicaldata eachEntry : clinicalData) {
            if (filters.containsKey(eachEntry.getComponentName())) {
                clinicalData.remove(eachEntry);
                continue;
            } else {
                filters.put(eachEntry.getComponentName(), null);
            }

            BMICalculator.calculateBMI(clinicalData, eachEntry);
        }

        filters.clear();
        return patient;
    }



}