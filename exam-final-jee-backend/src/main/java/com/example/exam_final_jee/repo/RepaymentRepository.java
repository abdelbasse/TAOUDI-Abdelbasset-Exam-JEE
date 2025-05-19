package com.example.exam_final_jee.repo;

import com.example.exam_final_jee.entities.Repayment;
import com.example.exam_final_jee.enums.RepaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;

public interface RepaymentRepository extends JpaRepository<Repayment, Long> {
    List<Repayment> findByCreditId(Long creditId);

    // Find repayments by type (INSTALLMENT/EARLY_REPAYMENT)
    List<Repayment> findByType(RepaymentType type);

    // Find repayments within a date range
    @Query("SELECT r FROM Repayment r WHERE r.date BETWEEN :start AND :end")
    List<Repayment> findRepaymentsBetweenDates(@Param("start") Date startDate, @Param("end") Date endDate);

    // Calculate total repaid amount for a credit
    @Query("SELECT COALESCE(SUM(r.amount), 0) FROM Repayment r WHERE r.credit.id = :creditId")
    Double getTotalRepaidAmountByCreditId(@Param("creditId") Long creditId);
}
