package com.example.exam_final_jee.entities;

import com.example.exam_final_jee.enums.CreditStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data // Ajout de l'annotation Data pour générer getters/setters
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CREDIT_TYPE")
public abstract class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date applicationDate;
    private Double amount;
    private Integer repaymentDuration; // in months
    private Double interestRate;

    @Enumerated(EnumType.STRING)
    private CreditStatus status; // IN_PROGRESS, ACCEPTED, REJECTED

    private Date acceptanceDate;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL)
    private List<Repayment> repayments;
}
