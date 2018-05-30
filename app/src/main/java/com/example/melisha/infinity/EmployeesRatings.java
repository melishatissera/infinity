package com.example.melisha.infinity;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
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

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("employeeName", EmployeeName);
        result.put("id", EmployeeID);
        result.put("ratings", EmployeesRatings);

        return result;
    }
}

