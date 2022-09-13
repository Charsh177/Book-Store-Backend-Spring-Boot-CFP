package com.bridgelabz.bookstore.services;

import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.entity.UserData;

import javax.validation.Valid;
import java.util.List;


public interface IUserService {

    UserData registerUser(UserDTO userDTO);

    String addUser(UserDTO userDTO);

    String verifyOtp(String email, Integer otp);

    List<UserData> getAllUsers();

    String loginUsers(String email, String password);

    String forgotPassword(String email, String password);

    Object getUserByToken(String token);

    Object getUserByEmailId(String emailId);

    UserData updateUser(String email, UserDTO userDTO);

    String getToken(String email);

    List<UserData> getAllUserDataByToken(String token);

    UserData updateRecordByToken(String token, UserDTO userDTO);

    UserData updateUserByToken(String token, UserDTO userDTO);

    UserData updateRecordById(Integer id, @Valid UserDTO userDTO);

}
