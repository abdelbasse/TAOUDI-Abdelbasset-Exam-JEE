package com.example.exam_final_jee.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditSimulationResponseDTO {
    private Double monthlyPayment;
    private Double totalInterest;
    private Double totalPayment;
}