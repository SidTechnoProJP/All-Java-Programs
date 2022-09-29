package com.srs.assignment;

import java.util.HashMap;
import java.util.Map;

public class Department {

    private HashMap<Integer, Student> studentHashMap;

    public Department(){
        studentHashMap = new HashMap<Integer, Student>();
    }

    public Boolean addStudent(int id, String name, String courseName)
    {
        if (studentHashMap.containsKey(id)) {
            return false;
        }
        Student students = new Student(id, name, courseName);
        studentHashMap.put(id, students);
        return true;
    }

    public boolean removeStudent(int id)
    {
        if (studentHashMap.containsKey(id)) {
            return false;
        }
        studentHashMap.remove(id);
        return true;
    }

    public Student findStudent(int id)
    {
        return studentHashMap.get(id);
    }



}
