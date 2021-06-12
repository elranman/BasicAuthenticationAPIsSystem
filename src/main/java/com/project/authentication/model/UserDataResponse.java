package com.project.authentication.model;

import java.io.Serializable;

public class UserDataResponse extends GeneralResponse implements Serializable {
    private String fullName;

    public UserDataResponse(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
