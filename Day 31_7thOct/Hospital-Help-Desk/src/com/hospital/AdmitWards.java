package com.hospital;


public class AdmitWards {
    private String wardName, wardId;
    private int maximumNumberOfPatientInWard;

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getWardId() {
        return wardId;
    }

    public void setWardId(String wardId) {
        this.wardId = wardId;
    }

    public int getMaximumNumberOfPatientInWard() {
        return maximumNumberOfPatientInWard;
    }

    public void setMaximumNumberOfPatientInWard(int maximumNumberOfPatientInWard) {
        this.maximumNumberOfPatientInWard = maximumNumberOfPatientInWard;
    }

    public AdmitWards(String wardName, String wardId, int maximumNumberOfPatientInWard) {
        this.wardName = wardName;
        this.wardId = wardId;
        this.maximumNumberOfPatientInWard = maximumNumberOfPatientInWard;
    }

    @Override
    public String toString() {
        return "AdmitWards{" +
                "wardName='" + wardName + '\'' +
                ", wardId='" + wardId + '\'' +
                ", maximumNumberOfPatientInWard=" + maximumNumberOfPatientInWard +
                '}';
    }
}
