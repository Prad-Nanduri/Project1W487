package com.example.project1w487;

import java.sql.Timestamp;

public class AccessFilterModel {

    String name;
    Integer userID;
    String accessType;
    Timestamp timestamp;

    public AccessFilterModel(String name, Integer userID, String accessType, Timestamp timestamp) {
        this.name = name;
        this.userID = userID;
        this.accessType = accessType;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public Integer getUserID() {
        return userID;
    }

    public String getAccessType() {
        return accessType;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
