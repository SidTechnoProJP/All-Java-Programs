package com.srs.assignment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//book
public class Elective {

    private int electiveID;
    String nameOfElectiveCourse;

    private Map<Integer, String> elective1 = new HashMap<>();
    private Map<Integer, String> elective2 = new HashMap<>();

    Elective(int electiveID, String nameOfElectiveCourse) {
        this.electiveID = electiveID;
        this.nameOfElectiveCourse = nameOfElectiveCourse;
    }

    public int getElectiveID() {
        return electiveID;
    }

    public void setElectiveID(int electiveID) {
        this.electiveID = electiveID;
    }

    public String getNameOfElectiveCourse() {
        return nameOfElectiveCourse;
    }

    public void setNameOfElectiveCourse(String nameOfElectiveCourse) {
        this.nameOfElectiveCourse = nameOfElectiveCourse;
    }

    public HashMap<Integer, String> getElective1() {
        return (HashMap<Integer, String>) elective1;
    }

    public HashMap<Integer, String> getElective2() {
        return (HashMap<Integer, String>) elective2;
    }

    public HashMap<Integer, String> showElective1()
    {
        elective1.put(1001,"Data Structures");
        elective1.put(1221,"Operating System");
        elective1.put(1351,"Computer Networks");

        return (HashMap<Integer, String>) elective1;
    }
    public HashMap<Integer, String> showElective2()
    {
        elective2.put(2551,"Fluid Mechanics");
        elective2.put(1221,"Thermodynamics");
        elective2.put(1351,"Heat Transfer");

        return (HashMap<Integer, String>) elective2;
    }
}
