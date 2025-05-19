package com.example.exam_final_jee.dtos;

import com.example.exam_final_jee.enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// RealEstateCreditDTO.java
@Data
public class RealEstateCreditDTO extends CreditDTO {
    private PropertyType propertyType;
}
