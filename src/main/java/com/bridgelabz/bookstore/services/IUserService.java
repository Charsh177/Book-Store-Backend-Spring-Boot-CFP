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

    String loginUser(String email_id, String password);

    String loginUsers(String email, String password);

    int loginUserTest(String email_id, String password);

    Object getUserById(String token);

    String forgotPassword(String email, String password);

    Object getUserByEmailId(String emailId);

    UserData updateUser(String email, UserDTO userDTO);

    String getToken(String email);

    List<UserData> getAllUserDataByToken(String token);

    UserData updateRecordByToken(String token, UserDTO userDTO);

    Integer loginUserId(String email_id);

    UserData getUserByID(int id);

    UserData updateRecordById(Integer id, @Valid UserDTO userDTO);

}




//    // User Registration
//
//    List<UserData> getUserData();
//
//    UserData registerUser(UserDTO userDTO);
//
//    Integer verifyOtp(String email, Integer otp);
//
//    String loginUser(String email, String password);
//
//    String getToken(String email);
//
//    UserData getUserDataByToken(String token);
//
//    UserData updateRecordByToken(String token, UserDTO userDTO);
//
//    String deleteRecordByToken(String token);
//
//    UserData getUserDataByIds(int userId);
//
//    int forgotPassword(String email, String password);
//
//    UserData getUserDataByEmail(String email);
//
////    int loginUserTest(String email, String password);
//
//    Object getIdByToken(String token);
//}