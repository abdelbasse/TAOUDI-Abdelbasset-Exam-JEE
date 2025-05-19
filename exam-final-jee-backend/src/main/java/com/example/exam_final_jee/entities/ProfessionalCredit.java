package com.example.exam_final_jee.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("PROFESSIONAL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionalCredit extends Credit {
    private String purpose;
    private String companyBusinessName;
}