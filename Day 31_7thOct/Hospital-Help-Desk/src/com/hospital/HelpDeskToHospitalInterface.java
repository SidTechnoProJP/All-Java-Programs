package com.hospital;


public interface HelpDeskToHospitalInterface {
    String generatePatientReferenceNumber(int lengthOfId);

    void generateBill(String patientId);

    void addInsuranceDetailsIfAvailable(String patientId);
}
