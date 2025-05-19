package com.example.exam_final_jee.dtos;

import lombok.Data;

@Data
public class CreditSimulationRequestDTO {
    private Double amount;
    private Double interestRate; // annual rate
    private Integer durationMonths;
}