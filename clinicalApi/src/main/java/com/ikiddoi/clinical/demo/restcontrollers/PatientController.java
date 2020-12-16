package com.ikiddoi.clinical.demo.restcontrollers;

import com.ikiddoi.clinical.demo.repos.PatientRepository;
import com.ikiddoi.clinical.demo.util.BMICalculator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;
import com.ikiddoi.clinical.demo.entities.Patient;
import com.ikiddoi.clinical.demo.entities.Clinicaldata;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PatientController {

    private PatientRepository patientRepository;
    Map<String,String> filters = new HashMap<String, String>();
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

    @GetMapping(value="/patient/analyse/{id}")
    public Patient analyse(@PathVariable("id") int id) {
        Patient patient = this.patientRepository.findById(id).get();
        System.out.println(patient.getId());
        List<Clinicaldata> clinicalData = patient.getClinicalData();

        ArrayList<Clinicaldata> duplicateClinicalData = new ArrayList<Clinicaldata>(clinicalData);

        for (Clinicaldata data : duplicateClinicalData) {
            if(filters.containsKey(data.getComponentName())) {
                clinicalData.remove(data);
                continue;
            }
            else {
                filters.put(data.getComponentName(), null);
            }

            BMICalculator.calculateBMI(clinicalData, data);
        }
        filters.clear();
        return patient;
    }




}