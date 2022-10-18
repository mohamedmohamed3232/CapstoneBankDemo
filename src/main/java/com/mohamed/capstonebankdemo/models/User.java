package com.mohamed.capstonebankdemo.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
/*
This is my model for the users table using persistence to map this entity
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    private int user_id;
    @NotEmpty(message = "The First Name field can not be empty")
    @Size(min = 3, message = "The First name must have at least 4 characters")
    private String first_name;
    @NotEmpty
    @Size(min = 3, message = "The Last Name must have at least 4 characters")
    private String last_name;
    @Email
    @NotEmpty
    @Pattern(regexp = "([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})", message = "Please Enter a Valid Email")
    private String email;
    @NotEmpty
    @NotNull
    private String password;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpated_at(LocalDateTime upated_at) {
        this.updated_at = upated_at;
    }

    public User() {
    }

    public User(int user_id, String first_name, String last_name, String email, String password, LocalDateTime created_at, LocalDateTime updated_at) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
