package com.example.pub.models;

import com.example.pub.security.SecurityConfiguration;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean isActive;
    private boolean isAdult;
    private Integer pocket;
    private String password;
    @Enumerated(EnumType.STRING)
    private SecurityConfiguration.UserRole role;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Commission> commissions;

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

    public List<Commission> getOrders() {
        return commissions;
    }

    public void setOrders(List<Commission> commissions) {
        this.commissions = commissions;
    }

    public void pay(Integer price) {
        this.pocket = pocket - price;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SecurityConfiguration.UserRole getRole() {
        return role;
    }

    public void setRole(SecurityConfiguration.UserRole role) {
        this.role = role;
    }

    public List<Commission> getCommissions() {
        return commissions;
    }

    public void setCommissions(List<Commission> commissions) {
        this.commissions = commissions;
    }
}
