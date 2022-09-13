package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserData, Integer> {
    @Query(value = "SELECT * FROM user where email=:email_Id", nativeQuery = true)
    public Optional<UserData> findByEmailid(String email_Id);

    public UserData findByUserId(int userId);

    @Modifying
    @Transactional
    @Query(value = "update user set verified=true where email = :email ", nativeQuery = true)
    void changeVerified(String email);

    @Query(value = "SELECT * FROM user where email=:email", nativeQuery = true)
    public UserData findByEmailId(String email);

    @Modifying
    @Transactional
    @Query(value = "update user set password = :newPassword where email = :email", nativeQuery = true)
    void updateNewPassword(String email, String newPassword);
}
