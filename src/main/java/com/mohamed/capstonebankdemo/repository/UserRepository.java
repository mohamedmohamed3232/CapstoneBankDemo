package com.mohamed.capstonebankdemo.repository;

import com.mohamed.capstonebankdemo.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/*
This is the repository for the User Table (connects to the user entity)
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    //Query in order to get the users email
    @Query(value = "SELECT email FROM users WHERE email = :email", nativeQuery = true)
    String getUserEmail(@Param("email") String email);
    //Query in order to get the users password
    @Query(value = "SELECT password FROM users WHERE email = :email", nativeQuery = true)
    String getUserPassword(@Param("email") String email);

    //Query to get the user by email
    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    User getUserDetails(@Param("email") String email);

    //Query in order to register the user
    @Modifying
    @Query(value ="INSERT INTO users(first_name, last_name, email, password) Values" +
            "(:first_name, :last_name, :email, :password)", nativeQuery = true)
    @Transactional
    void registerUser(@Param("first_name") String first_name,
                      @Param("last_name") String last_name,
                      @Param("email") String email,
                      @Param("password") String password);

}


