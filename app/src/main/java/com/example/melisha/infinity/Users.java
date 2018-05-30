package com.example.melisha.infinity;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Users {

    public String empName;
    public String empEmail;
    public String empPassword;
    public String empType;
    public String UserID;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }
    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        this.UserID = userID;
    }

    public Users() {
    }

    public Users(String name, String email, String employeeType, String Password,String userID) {
        this.empName = name;
        this.empEmail = email;
        this.empType = employeeType;
        this.empPassword = Password;
        this.UserID = userID;

    }

}