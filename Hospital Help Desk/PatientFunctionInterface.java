package IOSevaluationQuestion;

public interface PatientFunctionInterface {
    int viewDueBillAmount(String patientId);
    int payDueBills(String patientId);
    PatientDetails getPatientRecordFromHospital(String patientId);
}
