package com.bridgelabz.bookstore.dto;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
public class UserDTO {
    @Pattern(regexp = "^[A-Z]{1}[A-Za-z]{1,}$", message = "Invalid User Name")
    public String userName;
    public String userPassword;
    public String address;
    public long phoneNumber;
    @Email
    public String email;

    // User Registration and login
    public UserDTO(String email,String userPassword) {
        this.email = email;
        this.userPassword = userPassword;
    }
}