package IOSevaluationQuestion;

public class PatientDetails {
    private String patientName, patientGender, patientId/*, patientInsuranceId , billPaidStatus*/;
    private int patientAge, numberOfTimesPatientVisited,patientDueBillAmount/*, numberOfTimesAdmitted , partialAmountPaidDuringAdmission*/;
    private long PatientPhoneNumber;
    private PatientInsuranceDetails patientInsuranceDetails;

    public int getPatientDueBillAmount() {
        return patientDueBillAmount;
    }

    public void setPatientDueBillAmount(int patientDueBillAmount) {
        this.patientDueBillAmount = patientDueBillAmount;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public int getNumberOfTimesPatientVisited() {
        return numberOfTimesPatientVisited;
    }

    public void setNumberOfTimesPatientVisited(int numberOfTimesPatientVisited) {
        this.numberOfTimesPatientVisited = numberOfTimesPatientVisited;
    }

    public long getPatientPhoneNumber() {
        return PatientPhoneNumber;
    }

    public void setPatientPhoneNumber(long patientPhoneNumber) {
        PatientPhoneNumber = patientPhoneNumber;
    }

    public PatientInsuranceDetails getPatientInsuranceDetails() {
        return patientInsuranceDetails;
    }

    public void setPatientInsuranceDetails(PatientInsuranceDetails patientInsuranceDetails) {
        this.patientInsuranceDetails = patientInsuranceDetails;
    }

    PatientDetails(String patientId, String patientName, String patientGender, int patientAge, long patientPhoneNumber, int numberOfTimesPatientVisited/*, int numberOfTimesAdmitted, String patientInsuranceId , String billPaidStatus , int partialAmountPaidDuringAdmission*/) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientGender = patientGender;
        this.patientAge = patientAge;
        this.PatientPhoneNumber = patientPhoneNumber;
        this.numberOfTimesPatientVisited = numberOfTimesPatientVisited;
         /*this.numberOfTimesAdmitted = numberOfTimesAdmitted;
        this.patientInsuranceId = patientInsuranceId;
        this.billPaidStatus = billPaidStatus;
        this.partialAmountPaidDuringAdmission = partialAmountPaidDuringAdmission;*/
    }

    @Override
    public String toString() {
        return "PatientDetails{" +
                "patientName='" + patientName + '\'' +
                ", patientGender='" + patientGender + '\'' +
                ", patientId='" + patientId + '\'' +
                ", patientAge=" + patientAge +
                ", numberOfTimesPatientVisited=" + numberOfTimesPatientVisited +
                ", PatientPhoneNumber=" + PatientPhoneNumber +
                '}';
    }
}