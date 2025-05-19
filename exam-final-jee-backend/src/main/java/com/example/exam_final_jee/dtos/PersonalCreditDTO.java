package com.example.exam_final_jee.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// PersonalCreditDTO.java
@Data
public class PersonalCreditDTO extends CreditDTO {
    private String purpose;
}