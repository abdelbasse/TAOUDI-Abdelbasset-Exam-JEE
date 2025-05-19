package com.example.exam_final_jee.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PROFESSIONAL")
public class ProfessionalCredit extends Credit {
    private String purpose;
    private String companyBusinessName;
}