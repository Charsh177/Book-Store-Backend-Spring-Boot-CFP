package com.bridgelabz.bookstore.entity;

import com.bridgelabz.bookstore.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "user")
@AllArgsConstructor
@ToString
public @Data class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int userId;
    private String userName;
    private String userPassword;
    private String address;
    private long phoneNumber;
    @Email
    private String email;

    private Boolean verified;

    private Integer otp;

    public UserData() {

    }
    public UserData(UserDTO userDTO) {
        this.updateUser(userDTO);
    }

    public UserData(int userId, UserDTO userDTO) {
        this.userId = userId;
        this.userName = userDTO.getUserName();
        this.address = userDTO.getAddress();
        this.email = userDTO.getEmail();
        this.userPassword = userDTO.getUserPassword();
    }

    public void updateUser(UserDTO userDTO) {
        this.userName = userDTO.userName;
        this.userPassword = userDTO.userPassword;
        this.address = userDTO.address;
        this.phoneNumber = userDTO.phoneNumber;
        this.email = userDTO.email;
    }
}