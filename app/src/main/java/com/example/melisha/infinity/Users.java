package com.example.melisha.infinity;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Users {

    public String name;
    public String email;
    public Boolean employeeType;

    public Users() {
    }

    public Users(String name, String email, Boolean employeeType) {
        this.name = name;
        this.email = email;
        this.employeeType = employeeType;
    }

}