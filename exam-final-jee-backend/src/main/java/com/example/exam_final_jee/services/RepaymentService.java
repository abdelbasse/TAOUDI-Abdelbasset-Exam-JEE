package com.example.exam_final_jee.services;

import com.example.exam_final_jee.dtos.RepaymentDTO;
import com.example.exam_final_jee.enums.RepaymentType;
import com.example.exam_final_jee.exceptions.CreditNotFoundException;
import com.example.exam_final_jee.exceptions.RepaymentNotFoundException;

import java.util.Date;
import java.util.List;

public interface RepaymentService {
    RepaymentDTO saveRepayment(RepaymentDTO repaymentDTO) throws CreditNotFoundException;
    List<RepaymentDTO> getRepaymentsByCredit(Long creditId);
    List<RepaymentDTO> getRepaymentsByType(RepaymentType type);
    List<RepaymentDTO> getRepaymentsBetweenDates(Date startDate, Date endDate);
    Double getTotalRepaidAmount(Long creditId);
    RepaymentDTO getRepayment(Long repaymentId) throws RepaymentNotFoundException;
    void deleteRepayment(Long repaymentId);
}