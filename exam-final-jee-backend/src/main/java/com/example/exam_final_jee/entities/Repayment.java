package com.example.exam_final_jee.entities;

import com.example.exam_final_jee.enums.RepaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Repayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;          // Date when repayment was made
    private Double amount;      // Amount repaid

    @Enumerated(EnumType.STRING)
    private RepaymentType type; // INSTALLMENT or EARLY_REPAYMENT

    @ManyToOne
    @JoinColumn(name = "credit_id")
    private Credit credit;      // Link to the associated credit
}
