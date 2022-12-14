package com.mohamed.capstonebankdemo.repository;

import com.mohamed.capstonebankdemo.models.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
/**
This is the repository for the accounts this helps store and retrieve account info from the database
 */
@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
    //Query where we are getting all the accounts for the User by userId
    @Query(value = "SELECT * FROM accounts WHERE user_id = :user_id", nativeQuery = true)
    List<Account> getUserAccountsById(@Param("user_id") int user_id);

    //Query Where we are getting the balance for the total amount for all the accounts for that user
    // using userID
    @Query(value = "SELECT sum(balance) FROM accounts WHERE user_id = :user_id", nativeQuery = true)
    BigDecimal getTotalBalance(@Param("user_id") int user_id);

    // Query to get the users account balance through UserId
    @Query(value = "SELECT balance FROM accounts WHERE user_id = :user_id AND :account_id = account_id", nativeQuery = true)
    double getAccountBalance(@Param("user_id") int user_id, @Param("account_id") int account_id);

    //This is a query to update the balance after the user goes through a transaction
    @Modifying
    @Query(value = "UPDATE accounts SET balance = :new_balance WHERE account_id = :account_id", nativeQuery = true)
    @Transactional
    void changeAccountBalanceById(@Param("new_balance") double new_balance, @Param("account_id") int account_id);

    //Query where we are inserting the User details into the user database
    @Modifying
    @Query(value = "INSERT INTO accounts(user_id, account_number, account_name, account_type) VALUES" +
            "(:user_id, :account_number, :account_name,:account_type )", nativeQuery = true)
    @Transactional
    void createBankAccount(@Param("user_id") int user_id,
                           @Param("account_number") String account_number,
                           @Param("account_name") String account_name,
                           @Param("account_type") String account_type);
}
