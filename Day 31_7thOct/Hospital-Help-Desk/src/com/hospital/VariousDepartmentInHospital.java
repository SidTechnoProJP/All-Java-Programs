package com.hospital;

public class VariousDepartmentInHospital {
    private String departmentName, departmentId;
    private int maximumNumberOfPatient;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public int getMaximumNumberOfPatient() {
        return maximumNumberOfPatient;
    }

    public void setMaximumNumberOfPatient(int maximumNumberOfPatient) {
        this.maximumNumberOfPatient = maximumNumberOfPatient;
    }

    public VariousDepartmentInHospital(String departmentName, String departmentId, int maximumNumberOfPatient) {
        this.departmentName = departmentName;
        this.departmentId = departmentId;
        this.maximumNumberOfPatient = maximumNumberOfPatient;
    }

    @Override
    public String toString() {
        return "VariousDepartmentInHospital{" +
                "departmentName='" + departmentName + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", maximumNumberOfPatient=" + maximumNumberOfPatient +
                '}';
    }
}
