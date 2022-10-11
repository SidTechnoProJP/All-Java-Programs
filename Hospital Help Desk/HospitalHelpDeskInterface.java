package IOSevaluationQuestion;

import java.util.Set;

public interface HospitalHelpDeskInterface {
    Set<String> showVariousDepartmentAvailableInHospital();
    String patientRegistration(String patientName, String patientGender, int patientAge, long patientNumber, int numberOfTimesPatientVisited /*, int numberOfTimesAdmitted, String patientInsuranceId*/);
    PatientDetails getPatientDetails(String patientId);
    void allocateDepartmentToPatient(String patientId, String departmentId);
    void allocateWardForPatientToAdmit(String patientId);

}
