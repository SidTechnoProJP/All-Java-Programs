package com.spring.jpa.tutorial.repository;

import com.spring.jpa.tutorial.entity.Course;
import com.spring.jpa.tutorial.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CourseMaterialRepositoryTest {
    @Autowired
    private CourseMaterialRepository repository;

    @Test
    public void saveCourseMaterial(){
        Course course =
                Course.builder()
                .title("DSA")
                .credit(5)
                .build();

        CourseMaterial courseMaterial =
                CourseMaterial.builder()
                .url("www.google.com/course/1")
                .course(course)
                .build();

        repository.save(courseMaterial);
    }
}