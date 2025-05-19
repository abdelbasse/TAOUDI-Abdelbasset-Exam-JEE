package com.example.exam_final_jee.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("PERSONAL")
public class PersonalCredit extends Credit {
    private String purpose; // car purchase, studies, renovations, etc.
}