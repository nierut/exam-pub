package com.example.pub.models.DTOs;

import com.example.pub.models.User;

public class UserDTO {
    private Long id;
    private String name;
    private boolean isActive;
    private boolean isAdult;
    private Integer pocket;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.isActive = user.isActive();
        this.isAdult = user.isAdult();
        this.pocket = user.getPocket();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public Integer getPocket() {
        return pocket;
    }

    public void setPocket(Integer pocket) {
        this.pocket = pocket;
    }
}
