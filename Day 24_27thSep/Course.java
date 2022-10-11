import org.springframework.stereotype.Component;

@Component("vilas")
public class Course {
    Course() {
        System.out.println("new course");
    }


    void getCourse() {
        System.out.println("Course 1");

    }
}