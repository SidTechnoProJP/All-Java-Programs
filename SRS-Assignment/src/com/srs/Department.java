package com.srs;

public class Department {
    private Course course;
    private SRS srs;
    private Student student;
    public Department() {
        course=new Course();
        srs=new SRS();
    }
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
    public SRS getSrs() {
        return srs;
    }
    public void setSrs(SRS srs) {
        this.srs = srs;
    }
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }


}
