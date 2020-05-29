package com.qitai.entity;

public enum UserRoleType {
    USER("USER"),
    ADMIN("ADMIN");

    String userProfileType;

    private UserRoleType(String userProfileType){
        this.userProfileType = userProfileType;
    }

    public String getUserRoleType(){
        return userProfileType;
    }

}
