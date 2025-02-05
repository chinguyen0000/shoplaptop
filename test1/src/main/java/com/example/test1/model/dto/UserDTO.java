package com.example.test1.model.dto;

import com.example.test1.model.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

public class UserDTO implements Validator {
    private Integer id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    @NotBlank(message = "Address can not be blank")
    private String address;
    private Boolean gender; //true is female and false is male
    private Boolean status; //true is active and false is inactive
    private Role role;
    private LocalDate birthday;

    public UserDTO() {}

    public UserDTO(Integer id, String username, String password, String fullName, String email, String phone, String address, Boolean gender, Boolean status, Role role, LocalDate birthday) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
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

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", gender=" + gender +
                ", status=" + status +
                ", role=" + role +
                ", birthday=" + birthday +
                '}';
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO userDTO = (UserDTO) target;

        String username = userDTO.getUsername();
        if (username.trim().isEmpty()) {
            errors.rejectValue("username","empty.input");
        } else if (username.length() > 21 || username.length() < 5) {
            errors.rejectValue("username","","Username is too long or too short");
        } else if (!username.matches("^[a-zA-Z0-9]*$")) {
            errors.rejectValue("username","","Username contains invalid characters");
        }

        String fullName = userDTO.getFullName();
        if (fullName.trim().isEmpty()) {
            errors.rejectValue("fullName","empty.input");
        } else if (fullName.length() > 51 || fullName.length() < 5) {
            errors.rejectValue("fullName","","Full name is too long or too short");
        } else if (!fullName.matches("^([\\p{L}\\s]+)$")) {
            errors.rejectValue("fullName","","Full name contains invalid characters");
        }

        String password = userDTO.getPassword();
        if (password.trim().isEmpty()) {
            errors.rejectValue("password","empty.input");
        } else if (password.length() < 5) {
            errors.rejectValue("password","","Password must have at least 6 characters");
        }

        String email = userDTO.getEmail();
        if (email.trim().isEmpty()) {
            errors.rejectValue("email","empty.input");
        } else if (email.length() < 5) {
            errors.rejectValue("email","","Email must have at least 5 characters");
        } else if (!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            errors.rejectValue("email","","Email format is incorrect");
        }

        String phone = userDTO.getPhone();
        if (phone.trim().isEmpty()) {
            errors.rejectValue("phone","empty.input");
        } else if (phone.length() > 15 || phone.length() < 2) {
            errors.rejectValue("phone","","Phone number is too long or too short");
        } else if (!phone.matches("^\\+?\\d{10,11}$")) {
            errors.rejectValue("phone","","Phone number is invalid");
        }

        LocalDate birthday = userDTO.getBirthday();
        if (birthday == null) {
            errors.rejectValue("birthday","empty.input");
        } else if (LocalDate.now().getYear() <= birthday.getYear()) {
            errors.rejectValue("birthday","","Birthday is incorrect");
        }
    }
}
