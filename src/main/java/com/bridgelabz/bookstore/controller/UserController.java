package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.entity.UserData;
import com.bridgelabz.bookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    // register user with otp
    @PostMapping("/registerWithOtp")
    public ResponseEntity<ResponseDTO> registerUserOtp(@Valid @RequestBody UserDTO userDTO) {
        System.out.println(userDTO.getUserPassword());
        UserData userRegistration = userService.registerUser(userDTO);
        ResponseDTO responseDTO = new ResponseDTO("User Registered Successfully", userRegistration);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }


    // verify otp
    @GetMapping("/verifyOtp")
    public String verifyOtp(@RequestParam String email, @RequestParam Integer otp) {
        return userService.verifyOtp(email, otp);
    }

    // Login
    @GetMapping("/login")
    public String userLogin(@RequestParam String email,@RequestParam String password) {
        UserDTO userLoginDTO=new UserDTO(email, password);
        String response = userService.loginUsers(userLoginDTO.getEmail(),userLoginDTO.getUserPassword());
        return response;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> addUserInBookStore( @Valid @RequestBody UserDTO userDTO){
        String newUser= userService.addUser(userDTO);
        ResponseDTO responseDTO=new ResponseDTO("User Registered Successfully",newUser);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }

    // Get All Users
    @GetMapping(value = "/getAll")
    public ResponseEntity<String> getAllUser() {
        List<UserData> listOfUsers = userService.getAllUsers();
        ResponseDTO dto = new ResponseDTO("User retrieved successfully (:",listOfUsers);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    @GetMapping(value = "/getAll/{token}")
    public ResponseEntity<ResponseDTO> getAllUserDataByToken(@PathVariable String token) {
        List<UserData> listOfUser = userService.getAllUserDataByToken(token);
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully :::",listOfUser);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    // Get by email id
    @GetMapping("/getByEmailId/{emailId}")
    public ResponseEntity<ResponseDTO> getUserByEmailId(@PathVariable("emailId") String emailId) {
        return new ResponseEntity<ResponseDTO>( new ResponseDTO("Get User Data by Email",
                userService.getUserByEmailId(emailId)), HttpStatus.OK);
    }

    // Get user details by token
    @GetMapping("/getUserByToken/{token}")
    public ResponseEntity<ResponseDTO> getUserByToken(@PathVariable String token) {
        return new ResponseEntity<ResponseDTO>( new ResponseDTO("Get User Id / Data By Token :::",
                userService.getUserByToken(token)), HttpStatus.OK);
    }

    // Forget password reset
    @PostMapping("/forgotpassword")
    public ResponseEntity<String> forgotPassword(@RequestParam String email, @RequestParam String password) {
        String resPass = userService.forgotPassword(email,password);
        return new ResponseEntity(resPass, HttpStatus.OK);
    }

    // Update user by id
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateRecordById(@PathVariable Integer id,@RequestBody UserDTO userDTO){
        UserData entity = userService.updateRecordById(id,userDTO);
        ResponseDTO dto = new ResponseDTO("User Record updated successfully",entity);
        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
    }

    // Update user by email
    @PutMapping("/updateUserByEmail/{email}")
    public ResponseEntity<ResponseDTO> updateUserById(@PathVariable String email,@Valid @RequestBody UserDTO userDTO){
        UserData updateUser= userService.updateUser(email,userDTO);
        ResponseDTO dto = new ResponseDTO(" User Record updated successfully",updateUser);
        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
    }

    // Update User by token
    @PutMapping("/updatebyToken/{token}")
    public ResponseEntity<String> updateRecordById(@PathVariable String token,@Valid @RequestBody UserDTO userDTO){
        UserData entity = userService.updateRecordByToken(token,userDTO);
        ResponseDTO dto = new ResponseDTO("User Record updated successfully",entity);
        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
    }

    @GetMapping("/getToken/{email}")
    public ResponseEntity<ResponseDTO> getToken(@PathVariable String email) {
        String generatedToken = userService.getToken(email);
        ResponseDTO responseDTO=new ResponseDTO("Token for mail id sent on mail successfully",generatedToken);
        return new ResponseEntity(responseDTO,HttpStatus.OK);
    }
}