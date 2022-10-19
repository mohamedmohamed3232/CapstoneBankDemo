package com.mohamed.capstonebankdemo.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
This is my model for the accounts table using persistence to map this entity
 */
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    private int account_id;
    private int user_id;
    private String account_number;
    private String account_name;
    private String account_type;
    private BigDecimal balance;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Account() {
    }

    public Account(int account_id, int user_id, String account_number, String account_name, String account_type, BigDecimal balance, LocalDateTime created_at, LocalDateTime updated_at) {
        this.account_id = account_id;
        this.user_id = user_id;
        this.account_number = account_number;
        this.account_name = account_name;
        this.account_type = account_type;
        this.balance = balance;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account_id=" + account_id +
                ", user_id=" + user_id +
                ", account_number='" + account_number + '\'' +
                ", account_name='" + account_name + '\'' +
                ", account_type='" + account_type + '\'' +
                ", balance=" + balance +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
