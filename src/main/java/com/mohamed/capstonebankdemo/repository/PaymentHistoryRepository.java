package com.mohamed.capstonebankdemo.repository;

import com.mohamed.capstonebankdemo.models.PaymentHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentHistoryRepository extends CrudRepository<PaymentHistory, Integer> {
    @Query(value = "SELECT * FROM v_payments WHERE user_id = :user_id" , nativeQuery = true)
    List<PaymentHistory> getPaymentHistoriesByID(@Param("user_id") int user_id);
}