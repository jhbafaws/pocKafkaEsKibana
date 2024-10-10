package com.kafka.poc.user.event.consumer.dto;


import java.util.List;

public class UserDto {


    private String id;

    private String name;

    private String email;
    private String lastName;

    private String userName;

    private List<ActivityDto> activities;

    public UserDto() {
    }

    public UserDto(String id, String name, String email, String lastName, String userName, List<ActivityDto> activities) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.lastName = lastName;
        this.userName = userName;
        this.activities = activities;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<ActivityDto> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityDto> activities) {
        this.activities = activities;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", activities=" + activities +
                '}';
    }
}