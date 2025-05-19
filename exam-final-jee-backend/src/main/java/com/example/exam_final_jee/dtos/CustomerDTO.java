package com.example.exam_final_jee.dtos;

import lombok.Data;
import java.util.List;

@Data
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
    private List<CreditDTO> credits;
}