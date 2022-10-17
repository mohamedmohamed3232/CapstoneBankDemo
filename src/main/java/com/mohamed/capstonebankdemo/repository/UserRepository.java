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
    @Modifying
    @Query(value ="INSERT INTO users(first_name, last_name, email, password) Values" +
            "(:first_name, :last_name, :email, :password)", nativeQuery = true)
    @Transactional
    void registerUser(@Param("first_name") String first_name,
                      @Param("last_name") String last_name,
                      @Param("email") String email,
                      @Param("password") String password);

}

