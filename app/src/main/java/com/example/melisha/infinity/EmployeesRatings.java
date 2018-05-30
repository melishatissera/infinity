package com.example.melisha.infinity;

public class EmployeesRatings {

    String EmployeeID;
    String EmployeesRatings;
    String EmployeeName;

    public String getEmployeesRatings() {
        return EmployeesRatings;
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }

    public void setEmployeesRatings(String employeesRatings) {
        EmployeesRatings = employeesRatings;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public EmployeesRatings( String employeesRatings, String employeeName, String employeeID){
        this.EmployeesRatings = employeesRatings;
        this.EmployeeName = employeeName;
        this.EmployeeID = employeeID;
    }
}

