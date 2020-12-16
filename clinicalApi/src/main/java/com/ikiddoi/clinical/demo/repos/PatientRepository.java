package com.ikiddoi.clinical.demo.repos;


import com.ikiddoi.clinical.demo.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

	List<Patient> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);

}
