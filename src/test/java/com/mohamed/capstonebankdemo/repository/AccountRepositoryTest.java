package com.mohamed.capstonebankdemo.repository;

import com.mohamed.capstonebankdemo.models.Account;
import com.mohamed.capstonebankdemo.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountRepositoryTest {
    @Mock
    private AccountRepository accountRepository;
    User user = new User();


    @ParameterizedTest
    @ValueSource(ints = {123, 234})
    void getUserAccountsById(int userId) {
        User user = new User();
        user.setUser_id(userId);
        assertEquals(user.getUser_id(), userId);
    }

    @Test
    void getTotalBalance() {
        Account account = new Account();
        account.setBalance(BigDecimal.valueOf(123));
        assertEquals(account.getBalance(), new BigDecimal(123));
    }

    @Test
    void getAccountBalance() {
        Account account = new Account();
        account.setBalance(BigDecimal.valueOf(123));
        assertEquals(account.getBalance(), new BigDecimal(123));
    }

    @Test
    void updateAccountBalanceById() {
        Account account = new Account();
        account.setBalance(BigDecimal.valueOf(123));
        account.setBalance(BigDecimal.valueOf(1234));
        assertEquals(account.getBalance(), new BigDecimal(1234));
    }

    @Test
    void createBankAccount() {
        User user = new User();
        user.setUser_id(123);
        assertEquals(user.getUser_id(), 123);
    }
}