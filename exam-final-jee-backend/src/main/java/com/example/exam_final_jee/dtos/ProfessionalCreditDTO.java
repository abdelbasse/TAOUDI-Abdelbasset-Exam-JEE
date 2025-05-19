package com.example.exam_final_jee.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ProfessionalCreditDTO extends CreditDTO {
    private String purpose;
    private String companyBusinessName;
}
