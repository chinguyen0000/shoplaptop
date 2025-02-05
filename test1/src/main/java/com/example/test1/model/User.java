package com.example.test1.model;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true, nullable=false, columnDefinition = "varchar(20)")
    private String username;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false, columnDefinition = "varchar(50)")
    private String fullName;

    @Column(nullable=false, unique = true, columnDefinition = "varchar(50)")
    private String email;

    @Column(nullable=false, columnDefinition = "varchar(12)")
    private String phone;

    @Column(nullable=false, columnDefinition = "varchar(100)")
    private String address;

    @Column
    private Boolean gender; //true is female and false is male

    @Column
    private Boolean status; //true is active and false is inactive

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Role role;

    @Column(nullable = false)
    private LocalDate birthday;

    public User() {}

    public User(Integer id, String username, String password, String fullName, String email, String phone, String address, boolean gender, boolean status, Role role, LocalDate birthday) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
        this.status = status;
        this.role = role;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
