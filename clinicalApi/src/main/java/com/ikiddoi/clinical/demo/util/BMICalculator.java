package com.ikiddoi.clinical.demo.util;

import com.ikiddoi.clinical.demo.entities.Clinicaldata;
import com.ikiddoi.clinical.demo.entities.Patient;

import java.util.List;

public class BMICalculator {
    public static void calculateBMI(List<Clinicaldata> clinicaldataList, Clinicaldata eachEntry) {
        if (eachEntry.getComponentName().equals("hw")) {
            String[] heightAndWeight = eachEntry.getComponentValue().split("/");
            if (heightAndWeight.length > 1) {
                float feetToMetres = Float.parseFloat(heightAndWeight[0]) * 0.4536F;
                float BMI = Float.parseFloat(heightAndWeight[1]) / (feetToMetres * feetToMetres);
                Clinicaldata bmiEntry = new Clinicaldata();
                bmiEntry.setComponentName("BMI");
                bmiEntry.setComponentValue(Float.toString(BMI));
                clinicaldataList.add(bmiEntry);
            }
        }
    }
}
