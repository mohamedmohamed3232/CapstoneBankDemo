package com.mohamed.capstonebankdemo.repository;

import com.mohamed.capstonebankdemo.models.TransactionHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
This is the transactional history repository that helps get the transaction history from the database
 */
@Repository
public interface TransactHistoryRepository extends CrudRepository<TransactionHistory, Integer> {

    @Query(value = "SELECT * FROM v_transaction_history WHERE user_id = :user_id", nativeQuery = true)
    List<TransactionHistory> getTransactionRecordsById(@Param("user_id") int user_id);
}
