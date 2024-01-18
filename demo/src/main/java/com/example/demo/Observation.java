package com.example.demo;

import java.util.UUID;

public class Observation {
    private String MSCode;
    private String year;
    private String estCode;
    private Float estimate;
    private Float se;
    private Float lowerCIB;
    private Float upperCIB;
    private String flag;
    private String _id;

    public Observation() {
        this._id = UUID.randomUUID().toString();
    }

    public String getMSCode() {
        return MSCode;
    }

    public void setMSCode(String MSCode) {
        this.MSCode = MSCode;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getEstCode() {
        return estCode;
    }

    public void setEstCode(String estCode) {
        this.estCode = estCode;
    }

    public Float getEstimate() {
        return estimate;
    }

    public void setEstimate(Float estimate) {
        this.estimate = estimate != null ? estimate : 0.0f; // Asign 0.0 if the value is null
    }

    public Float getSe() {
        return se;
    }

    public void setSe(Float se) {
        this.se = se != null ? se : 0.0f; // Asign 0.0 if the value is null
    }

    public Float getLowerCIB() {
        return lowerCIB;
    }

    public void setLowerCIB(Float lowerCIB) {
        this.lowerCIB = lowerCIB != null ? lowerCIB : 0.0f; // Asign 0.0 if the value is null
    }

    public Float getUpperCIB() {
        return upperCIB;
    }

    public void setUpperCIB(Float upperCIB) {
        this.upperCIB = upperCIB != null ? upperCIB : 0.0f; // Asign 0.0 if the value is null
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String get_id() {
        return _id;
    }
}