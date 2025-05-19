package com.example.exam_final_jee.dtos;

import com.example.exam_final_jee.enums.CreditStatus;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class CreditDTO {
    private Long id;
    private Date applicationDate;
    private Double amount;
    private Integer repaymentDuration;
    private Double interestRate;
    private CreditStatus status;
    private Date acceptanceDate;
    private Long customerId;
    private List<RepaymentDTO> repayments;
}