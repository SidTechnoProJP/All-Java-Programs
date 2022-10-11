package IOSevaluationQuestion;

import java.util.*;

public class HelpDesk {
    static Scanner scan = new Scanner(System.in);
    static HelpDeskToHospitalInterface helpDeskToHospitalInterface;
    static PatientFunctionInterface patientFunctionInterface;
    static HospitalHelpDeskInterface hospitalHelpDeskInterface;
    public  void callHelpDesk(){
        while (true) {
            System.out.println("Enter \n1:Opd Registration. \n2:Admit Registration.\n3:Patient Request For Record.\n4:Exit.");
            int choice = scan.nextInt();
            switch (choice) {
                case 1 -> OpdRegistration();
                case 2 -> AdmitRegistration();
                case 3 -> patientRequestForRecord();
                case 4 -> {return;}
            }
        }
    }

     static void patientRequestForRecord() {
        System.out.println("enter the patient Id.");
        String patientId = scan.next();
        System.out.println("Enter\n1:view due bills.\n2:Pay due bills.\n3:Get Patient Record.");
        int choice = scan.nextInt();
        switch (choice) {
            case 1 -> System.out.println("Amount to be paid : " + patientFunctionInterface.viewDueBillAmount(patientId));
            case 2 -> System.out.println("Amount paid to hospital : " + patientFunctionInterface.payDueBills(patientId));
            case 3 -> {
                if (patientFunctionInterface.getPatientRecordFromHospital(patientId) == null)
                    System.out.println("Please pay the bill before request for patient record");
                else
                    System.out.println(patientFunctionInterface.getPatientRecordFromHospital(patientId));
            }
        }
    }


     static void AdmitRegistration() {
        System.out.println("Enter \n1:New registration \n2:If already registered");
        int choice = scan.nextInt();
        switch (choice) {
            case 1 -> newRegistrationDetails();
            case 2 -> getInformationAboutTheRegisteredPatient();
        }
    }

     static void OpdRegistration() {
        System.out.println("Enter \n1:New registration \n2:If already registered");
        int choice = scan.nextInt();
        switch (choice) {
            case 1 -> newRegistrationDetails();
            case 2 -> getInformationAboutTheRegisteredPatient();
        }

    }

     static void getInformationAboutTheRegisteredPatient() {
        System.out.println("Enter the patient reference id.");
        String patientId = scan.next();
        PatientDetails patientDetails = hospitalHelpDeskInterface.getPatientDetails(patientId);
        try {
            System.out.println("Please verify your details.");
            System.out.println(patientDetails);
            System.out.println("confirm [ADMIT or OPD].");
            String department;
            try{
                department = String.valueOf(SelectAdmitOrOpd.valueOf(scan.next().toUpperCase()));
                if (department.equals(SelectAdmitOrOpd.OPD.toString())) {
                    System.out.println(hospitalHelpDeskInterface.showVariousDepartmentAvailableInHospital());
                    System.out.println("enter the department ID to allocate patient.");
                    String departmentId = null;
                    try {
                        departmentId = String.valueOf(AvailableDepartmentInHospital.valueOf(scan.next()));
                    } catch (Exception exception) {
                        System.out.println("Invalid Department Choice." + exception);
                    }
                    hospitalHelpDeskInterface.allocateDepartmentToPatient(patientId, departmentId);
                }
                else if (department.equals(SelectAdmitOrOpd.ADMIT.toString())){
                    helpDeskToHospitalInterface.addInsuranceDetailsIfAvailable(patientId);
                    hospitalHelpDeskInterface.allocateWardForPatientToAdmit(patientId);
                }
            }catch (Exception exception){
                System.out.println("Select Proper option.");
            }
        } catch (Exception exception) {
            System.out.println("Invalid patient Id." + exception);
        }
    }

     static void newRegistrationDetails() {
        System.out.println("Enter your name.");
        String patientName = scan.next();
        System.out.println("Select gender(MALE,FEMALE,OTHERS).");
        String patientGender = null;
        try {
            patientGender = String.valueOf(Gender.valueOf(scan.next().toUpperCase()));
        } catch (Exception exception) {
            System.out.println("Invalid Gender Choice." + exception);
        }
        System.out.println("Enter age.");
        int patientAge = scan.nextInt();
        System.out.println("Enter telephone number.");
        long patientNumber = scan.nextLong();
        String patientId = hospitalHelpDeskInterface.patientRegistration(patientName, patientGender, patientAge, patientNumber, 1 /*, numberOfTimesAdmitted, null*/);
        verifyPatientDetailAndProceed(patientId);
    }

    private static void verifyPatientDetailAndProceed(String patientId) {
        PatientDetails patientDetails = hospitalHelpDeskInterface.getPatientDetails(patientId);
        try {
            System.out.println("Please verify your details.");
            System.out.println(patientDetails);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        System.out.println("confirm [ADMIT or OPD].");
        String department;
        try {
            department = String.valueOf(SelectAdmitOrOpd.valueOf(scan.next().toUpperCase()));
            if (department.equals(SelectAdmitOrOpd.OPD.toString())) {
                System.out.println(hospitalHelpDeskInterface.showVariousDepartmentAvailableInHospital());
                System.out.println("enter the department ID to allocate patient.");
                String departmentId = null;
                try {
                    departmentId = String.valueOf(AvailableDepartmentInHospital.valueOf(scan.next()));
                } catch (Exception exception) {
                    System.out.println("Invalid Department Choice." + exception);
                }
                hospitalHelpDeskInterface.allocateDepartmentToPatient(patientId, departmentId);
            } else {
                helpDeskToHospitalInterface.addInsuranceDetailsIfAvailable(patientId);
                hospitalHelpDeskInterface.allocateWardForPatientToAdmit(patientId);
            }
        }catch (Exception exception){
            System.out.println("Select Proper option.");
        }
    }

}
