package com.hospital;

import java.util.*;

class Hospital implements HospitalInterface, PatientFunctionInterface , HospitalHelpDeskInterface ,HelpDeskToHospitalInterface {
    static Scanner scan = new Scanner(System.in);
    static Map<String, PatientDetails> patientRegistrationDetails = new LinkedHashMap<>();
    static Map<String, VariousDepartmentInHospital> departmentInHospital = new HashMap<>();
    static Map<String, AdmitWards> wardsInHospital = new HashMap<>();
    HelpDesk helpDesk = new HelpDesk();
    @Override
    public void callHospital(){
        addVariousDepartmentInHospital();
        addVariousWardsInHospital();
        helpDesk.callHelpDesk();
    }
    @Override
    public void addVariousDepartmentInHospital() {
        departmentInHospital.put("DPT01", new VariousDepartmentInHospital("Cardiology", "DP01", 50));
        departmentInHospital.put("DPT02", new VariousDepartmentInHospital("OPD", "DP02", 100));
        departmentInHospital.put("DPT03", new VariousDepartmentInHospital("Dental", "DP03", 80));
        departmentInHospital.put("DPT04", new VariousDepartmentInHospital("Radiology", "DP04", 20));
    }

    @Override
    public void addVariousWardsInHospital() {
        wardsInHospital.put("WD01", new AdmitWards("Cardiology", "WD01", 5));
        wardsInHospital.put("WD02", new AdmitWards("Dental", "WD01", 50));
        wardsInHospital.put("WD03", new AdmitWards("Radiology", "WD02", 10));
        wardsInHospital.put("WD04", new AdmitWards("general", "WD03", 100));
    }

    @Override
    public Set<String> showVariousDepartmentAvailableInHospital() {
        return departmentInHospital.keySet();
    }

    @Override
    public String generatePatientReferenceNumber(int lengthOfId) {
        Random random = new Random();
        StringBuilder patientId = new StringBuilder();
        int lengthOfChar = lengthOfId - 3;
        int lengthOfInt = lengthOfId - lengthOfChar;
        for (int index = 0; index < lengthOfChar; index++) {
            char character = (char) (97 + random.nextInt(26));
            patientId.append(character);
        }
        for (int index = 0; index < lengthOfInt; index++) {
            char number = (char) (48 + random.nextInt(10));
            patientId.append(number);
        }
        return patientId.toString();
    }

    @Override
    public String patientRegistration(String patientName, String patientGender, int patientAge, long patientNumber, int numberOfTimesPatientVisited /*, int numberOfTimesAdmitted, String patientInsuranceId*/) {
        String patientId = generatePatientReferenceNumber(10);
        if (patientRegistrationDetails.containsKey(patientId))
            patientRegistration(patientName, patientGender, patientAge, patientNumber, numberOfTimesPatientVisited  /*, numberOfTimesAdmitted, patientInsuranceId*/);
        else
            patientRegistrationDetails.put(patientId, new PatientDetails(patientId, patientName, patientGender, patientAge, patientNumber, numberOfTimesPatientVisited /*, numberOfTimesAdmitted, patientInsuranceId*/));
        return patientId;
    }

    @Override
    public PatientDetails getPatientDetails(String patientId) {
        return patientRegistrationDetails.getOrDefault(patientId, null);
    }

    public void updateNumberOfTimesPatientVisited(String patientId) {
        int numberOfTimesPatientVisited = patientRegistrationDetails.get(patientId).getNumberOfTimesPatientVisited();
        patientRegistrationDetails.get(patientId).setNumberOfTimesPatientVisited(++numberOfTimesPatientVisited);
    }

    @Override
    public void allocateDepartmentToPatient(String patientId, String departmentId) {
        if (sendDetailsToDepartment(patientId).equalsIgnoreCase("YES") && departmentInHospital.get(departmentId).getMaximumNumberOfPatient() > 0) {
            int maximumNumberOfPatient = departmentInHospital.get(departmentId).getMaximumNumberOfPatient();
            departmentInHospital.get(departmentId).setMaximumNumberOfPatient(--maximumNumberOfPatient);
            updateNumberOfTimesPatientVisited(patientId);
            System.out.println("Doctor checking  the patient.");
            generateBill(patientId);
        } else System.out.println("Cannot fix the Appointment today.");
    }

    public String sendDetailsToDepartment(String patientId) {
        System.out.println(patientRegistrationDetails.get(patientId).toString());
        System.out.println("Waiting for reply from department.");
        return scan.next();
    }

    @Override
    public void generateBill(String patientId) {
        System.out.println("Bill generating...");
        int billAmount = scan.nextInt();
        patientRegistrationDetails.get(patientId).setPatientDueBillAmount(billAmount);
    }

    @Override
    public void allocateWardForPatientToAdmit(String patientId) {

    }

    @Override
    public void addInsuranceDetailsIfAvailable(String patientId) {

    }

    @Override
    public int viewDueBillAmount(String patientId) {
        return patientRegistrationDetails.get(patientId).getPatientDueBillAmount();
    }

    @Override
    public int payDueBills(String patientId) {
        int totalAmountPaid = patientRegistrationDetails.get(patientId).getPatientDueBillAmount();
        patientRegistrationDetails.get(patientId).setPatientDueBillAmount(0);
        return totalAmountPaid;
    }

    @Override
    public PatientDetails getPatientRecordFromHospital(String patientId) {
        if (patientRegistrationDetails.get(patientId).getPatientDueBillAmount() == 0)
            return patientRegistrationDetails.get(patientId);
        else return null;
    }
}