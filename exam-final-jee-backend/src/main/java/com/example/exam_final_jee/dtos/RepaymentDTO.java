package com.example.exam_final_jee.dtos;

import com.example.exam_final_jee.enums.RepaymentType;
import lombok.Data;
import java.util.Date;

@Data
public class RepaymentDTO {
    private Long id;
    private Date date;
    private Double amount;
    private RepaymentType type;
    private Long creditId;
}