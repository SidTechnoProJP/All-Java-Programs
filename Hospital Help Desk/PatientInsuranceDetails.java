package IOSevaluationQuestion;

public class PatientInsuranceDetails{
    String insureName , gender , age;
    long insuranceAmount;

    public PatientInsuranceDetails(String insureName, String gender, String age , long insuranceAmount) {
        this.insureName = insureName;
        this.gender = gender;
        this.age = age;
        this.insuranceAmount = insuranceAmount;
    }

    @Override
    public String toString() {
        return "PatientInsuranceDetails{" +
                "insureName='" + insureName + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                ", insuranceAmount=" + insuranceAmount +
                '}';
    }
}
