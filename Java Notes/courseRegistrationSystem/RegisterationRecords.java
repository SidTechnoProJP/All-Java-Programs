package courseRegistrationSystem;

import java.util.ArrayList;
import java.util.HashMap;

public class RegisterationRecords {
    private static RegisterationRecords instance = null;
    HashMap<String, CourseDetails> courses = new HashMap<>();
    HashMap<String, Student> students = new HashMap<>();
    HashMap<String, ArrayList<String>> studentRegisterdCourses = new HashMap<>();

    public static RegisterationRecords getInstance() {
        if (instance == null)
            instance = new RegisterationRecords();

        return instance;
    }


}
