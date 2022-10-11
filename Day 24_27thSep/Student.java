import org.springframework.stereotype.Component;

@Component
public class Student {

    Course course;


    Student(Course course) {
        this.course = course;
    }

    void getStudentCourse() {
        course.getCourse();
    }
}