package com.example.melisha.infinity;

public class EmployeesRatings {

    float EmployeesRatings;
    String EmployeeName;

    public float getEmployeesRatings() {
        return EmployeesRatings;
    }

    public void setEmployeesRatings(float employeesRatings) {
        EmployeesRatings = employeesRatings;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }
    public EmployeesRatings( float employeesRatings, String employeeName){
        this.EmployeesRatings = employeesRatings;
        this.EmployeeName = employeeName;
    }
}

