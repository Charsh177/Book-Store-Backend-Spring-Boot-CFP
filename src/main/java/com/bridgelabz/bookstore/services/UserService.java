package com.bridgelabz.bookstore.services;

import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.email.EmailService;
import com.bridgelabz.bookstore.entity.UserData;
import com.bridgelabz.bookstore.exceptions.BookException;
import com.bridgelabz.bookstore.exceptions.UserException;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.util.TokenUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService implements IUserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    TokenUtility util;

    // register user

    @Override
    public UserData registerUser(UserDTO userDTO) {
        System.out.println(userDTO.getUserPassword());
        userDTO.setUserPassword(passwordEncoder.encode(userDTO.getUserPassword()));
        System.out.println(userDTO.getUserPassword());
        UserData user = modelMapper.map(userDTO, UserData.class);
        String otp = getRandomNumberString();
        Integer intOtp = Integer.parseInt(otp);
        user.setOtp(intOtp);
        userRepository.save(user);
        String token = util.createToken(user.getUserId());
        emailService.sendEmail(user.getEmail(), "Otp for Verification", "Otp sent for verification purpose. \n"
                + user.getUserName() + " Please Click here to verify the Otp :-   "
                + "http://localhost:8080/user/verifyOtp" + intOtp
                + "\nToken :- " + token);
        return user;
    }

    @Override
    public String addUser(UserDTO userDTO) {
        UserData newUser= new UserData(userDTO);
        userRepository.save(newUser);
        String token = util.createToken(newUser.getUserId());
        emailService.sendEmail(newUser.getEmail(), "Sample Mail", "Registered SuccessFully, hii: "
                +newUser.getUserName()+" Please Click here to get data-> "
                +"http://localhost:8080/user/getAll/"+token);  //or getBy
        return token;
    }

    // verify otp

    @Override
    public String verifyOtp(String email, Integer otp) {
        UserData user = userRepository.findByEmailId(email);
        if (user == null)
            return "User Not Found..!!!";
        Integer userOtpFromServer = user.getOtp();
        if (!(otp.equals(userOtpFromServer)))
            return "Otp mis-match!";
        userRepository.changeVerified(email);
        emailService.sendEmail(user.getEmail(), "Verification Successful", " Hi " + user.getUserName() + ", You have successfully " +
                "verified your account. You can now login using Your email and password using this link. " + "http://localhost:8080/user/login");
        return "Verification successful, you are now verified user";
    }

    // random password for otp generate

    public static String getRandomNumberString() {
        Random random = new Random();
        int number = random.nextInt(999999);
        return String.format("%06d", number);
    }

    // login user

    @Override
    public String loginUsers(String email, String password) {
        UserData userLogin = userRepository.findByEmailId(email);
        if (userLogin == null)
            return "User not found";
        if (userLogin.getVerified() == null)
            return "User not verified. First do the verification and try again. ";
        if (!(passwordEncoder.matches(password, userLogin.getUserPassword())))
            return "User name or password incorrect";
        return "User Login successfully and token is " + getToken(userLogin.getEmail());
    }

    // get all users

    @Override
    public List<UserData> getAllUsers() {
        List<UserData> getUsers= userRepository.findAll();
        return getUsers;
    }

    // Get id by token

    @Override
    public Object getUserByToken(String token) {
        int id=util.decodeToken(token);
        Optional<UserData> getUser=userRepository.findById(id);
        if(getUser.isPresent()) {
            emailService.sendEmail("harshchougule177@gmail.com", "Sample Mail", " Get your data with this token, hii: "
                    +getUser.get().getEmail()+" Please Click here to get data-> " + "http://localhost:8080/user/getBy/"+token);
            return getUser;
        } else {
            throw new UserException("Record for provided userId is not found");
        }
    }

    @Override
    public String forgotPassword(String email, String password) {
        Optional<UserData> isUserPresent = userRepository.findByEmailid(email);
        if(isUserPresent.isEmpty()) {
            throw new UserException("User record doesn't found..!!!");
        } else {
            UserData user = isUserPresent.get();
            user.setUserPassword(password);
            userRepository.save(user);
            return "Password updated successfully";
        }
    }

    @Override
    public Object getUserByEmailId(String emailId) {
        return userRepository.findByEmailid(emailId);
    }

    @Override
    public UserData updateUser(String email_id, UserDTO userDTO) {
        Optional<UserData> updateUser = userRepository.findByEmailid(email_id);
        if(updateUser.isPresent()) {
            UserData newUser = new UserData(updateUser.get().getUserId(),userDTO);
            userRepository.save(newUser);
            String token = util.createToken(newUser.getUserId());
            emailService.sendEmail(newUser.getEmail(),"Welcome "+newUser.getUserName(),"Click here \n http://localhost:8080/user/getBy/"+token);
            return newUser;
        }
        throw new BookException("User Details for email not found..!!!");
    }

    @Override
    public String getToken(String email) {
        Optional<UserData> userRegistration=userRepository.findByEmailid(email);
        String token=util.createToken(userRegistration.get().getUserId());
        System.out.println(token);
        emailService.sendEmail(userRegistration.get().getEmail(),"Welcome " + userRegistration.get().getUserName(),"Token for changing password is : "+token);
        return token;
    }

    @Override
    public List<UserData> getAllUserDataByToken(String token) {
        int id = util.decodeToken(token);
        Optional<UserData> isContactPresent=userRepository.findById(id);
        if(isContactPresent.isPresent()) {
            List<UserData> listOfUsers=userRepository.findAll();
            emailService.sendEmail("harshchougule177@gmail.com", "Sample Mail", "Get your data with this token, hii: "
                    +isContactPresent.get().getEmail()+" Please Click here to get data-> "
                    +"http://localhost:8080/user/getAll/"+token);
            return listOfUsers;
        }else {
            System.out.println("Exception ...Token not found..!!!");
            return null;	}
    }

    @Override
    public UserData updateRecordByToken(String token, UserDTO userDTO) {
        return null;
    }

    @Override
    public UserData updateUserByToken(String token, UserDTO userDTO) {
        Integer id= util.decodeToken(token);
        Optional<UserData> addressBook = userRepository.findById(id);
        if(addressBook.isPresent()) {
            UserData newBook = new UserData(id,userDTO);
            userRepository.save(newBook);
            emailService.sendEmail(newBook.getEmail(), "Sample Mail", "Updated SuccessFully, hii: "
                    +newBook.getUserName()+" Please Click here to get data of updated id-> "
                    +"http://localhost:8080/user/update/"+token);
            return newBook;
        }
        throw new UserException("User Details for id not found..!!!");
    }

    @Override
    public UserData updateRecordById(Integer id, @Valid UserDTO userDTO) {
        Optional<UserData> addressBook = userRepository.findById(id);
        if(addressBook.isPresent()) {
            UserData newBook = new UserData(id,userDTO);
            userRepository.save(newBook);
            return newBook;
        }
        throw new UserException("User Details for id not found..!!!");
    }

}