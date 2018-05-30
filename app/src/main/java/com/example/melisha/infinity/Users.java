package com.example.melisha.infinity;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Users {

    public String name;
    public String email;
    public Boolean employeeType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(Boolean employeeType) {
        this.employeeType = employeeType;
    }

    public Users() {
    }

    public Users(String name, String email, Boolean employeeType) {
        this.name = name;
        this.email = email;
        this.employeeType = employeeType;
    }

}