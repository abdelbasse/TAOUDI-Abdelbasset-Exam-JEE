package com.example.exam_final_jee.repo;

import com.example.exam_final_jee.entities.Credit;
import com.example.exam_final_jee.enums.CreditStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;

public interface CreditRepository extends JpaRepository<Credit, Long> {
    // Find credits by customer ID
    List<Credit> findByCustomerId(Long customerId);

    // Find credits by status
    List<Credit> findByStatus(CreditStatus status);

    // Search credits within a date range
    @Query("SELECT c FROM Credit c WHERE c.applicationDate BETWEEN :start AND :end")
    List<Credit> findCreditsBetweenDates(@Param("start") Date startDate, @Param("end") Date endDate);

    // Find credits with amount greater than
    List<Credit> findByAmountGreaterThan(Double amount);
}