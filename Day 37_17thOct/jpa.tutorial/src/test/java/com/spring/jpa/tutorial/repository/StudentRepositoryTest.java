package com.spring.jpa.tutorial.repository;

import com.spring.jpa.tutorial.entity.Guardian;
import com.spring.jpa.tutorial.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest

class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    /*@Test
    public void saveStudent(){
        Student student = Student.builder()
                .emailId("sidsawant@gmail.com")
                .firstName("Siddhesh")
                .lastName("Sawant")
               // .guardianName("Uday")
               // .guardianEmail("uhsawant@gmail.com")
               // .guardianMobile("9730120460")
                .build();

        studentRepository.save(student);

        *//*Student student1 = Student.builder()
                .emailId("sid@gmail.com")
                .firstName("Sid")
                .lastName("Sawant")
                .guardianName("Uday")
                .guardianEmail("uhsawant@gmail.com")
                .guardianMobile("9999999999")
                .build();

        studentRepository.save(student1);*//*
    }*/

    @Test
    public void saveStudentWithGuardian(){

        Guardian guardian = Guardian.builder()
                .name("Deepak")
                .email("deepak@gmail.com")
                .mobile("8385976481")
                .build();

        Student student = Student.builder()
                .firstName("Shivam")
                .emailId("shivam@gmail.com")
                .lastName("Verma")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printAllStudent(){
        List<Student> studentList =
                studentRepository.findAll();

        System.out.println("studentList = " + studentList);
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> students = studentRepository.findByFirstName("shivam");

        System.out.println("students = " + students);
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> students = studentRepository.findByFirstNameContaining("hi");

        System.out.println("students = " + students);
    }

    @Test
    public void printStudentBasedOnGuardianName(){
        List<Student> students = studentRepository.findByGuardianName("deepak");

        System.out.println("students = " + students);
    }

    @Test
    public void printGetStudentByEmailAddress(){
        Student student = studentRepository.getStudentByEmailAddress("sid@gmail.com");

        System.out.println("student = " + student);
    }

    @Test
    public void printGetStudentFirstNameByEmailAddress(){
        String firstName = studentRepository.getStudentFirstNameByEmailAddress("sidsawant@gmail.com");

        System.out.println("firstName = " + firstName);
    }

    @Test
    public void printgetStudentByEmailAddressNative(){
        Student student = studentRepository.getStudentByEmailAddressNative("sid@gmail.com");
        System.out.println("student = " + student);
    }


    @Test
    public void printgetStudentByEmailAddressNativeNamedParam(){
        Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("shivam@gmail.com");
        System.out.println("student = " + student);
    }

    @Test
    public void printUpdateStudentNameByEmailId(){
        studentRepository.updateStudentNameByEmailId("Siddhi", "sid@gmail.com");
    }
}