package courseRegistrationSystem;

public class CourseDetails {
    private String courseName;
    private String courseId;


    private int seatsLeft;


    private int durationOfCourse;

    CourseDetails(String courseId, String courseName, int seatsLeft, int durationOfCourse) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.seatsLeft = seatsLeft;
        this.durationOfCourse = durationOfCourse;

    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public int getSeatsLeft() {
        return seatsLeft;
    }

    /**
     * setter method for seats Left
     *
     * @param seatsLeft
     */
    public void setSeatsLeft(int seatsLeft) {
        this.seatsLeft = seatsLeft;
    }

    public int getDurationOfCourse() {
        return durationOfCourse;
    }

    /**
     * setter method for duration of Course
     *
     * @param durationOfCourse
     */
    public void setDurationOfCourse(int durationOfCourse) {
        this.durationOfCourse = durationOfCourse;
    }

    /**
     * Decrease the seats available for the course
     */
    public void decreaseSeatsAvailable() {
        this.seatsLeft--;
    }

    /**
     * Increase the seats available for the course
     */
    public void increaseSeatsAvailable() {
        this.seatsLeft++;
    }


}
