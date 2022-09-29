package srs.newsrs;

public class Registration {

    private Student theStudent;
    private CourseOffering theOffering;

    public Student getTheStudent() {
        return theStudent;
    }

    public void setTheStudent(Student st) {
        theStudent = st;
    }

    public CourseOffering getOffering() {
        return theOffering;
    }

    public void setOffering(CourseOffering of) {
        theOffering = of;
    }

    public String toString() {
        return theStudent + "\n" + theOffering;
    }
}
