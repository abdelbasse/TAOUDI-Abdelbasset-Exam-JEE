package com.example.exam_final_jee.entities;

import com.example.exam_final_jee.enums.PropertyType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@DiscriminatorValue("REAL_ESTATE")
public class RealEstateCredit extends Credit {
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType; // APARTMENT, HOUSE, COMMERCIAL
}