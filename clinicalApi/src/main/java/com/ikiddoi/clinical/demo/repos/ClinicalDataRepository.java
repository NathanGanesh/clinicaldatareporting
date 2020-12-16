package com.ikiddoi.clinical.demo.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import com.ikiddoi.clinical.demo.entities.Clinicaldata;
import java.util.List;

public interface ClinicalDataRepository extends JpaRepository<Clinicaldata, Integer> {

	List<Clinicaldata> findByPatientIdAndComponentNameOrderByMeasuredDateTime(int patientId, String componentName);

}
