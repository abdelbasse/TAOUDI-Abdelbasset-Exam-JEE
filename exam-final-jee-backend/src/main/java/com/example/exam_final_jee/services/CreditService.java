package com.example.exam_final_jee.services;

import com.example.exam_final_jee.dtos.*;
import com.example.exam_final_jee.enums.CreditStatus;
import com.example.exam_final_jee.exceptions.CreditNotFoundException;
import com.example.exam_final_jee.exceptions.CustomerNotFoundException;

import java.util.Date;
import java.util.List;

public interface CreditService {
    // Common credit operations
    CreditDTO getCredit(Long creditId) throws CreditNotFoundException;
    List<CreditDTO> listCredits();
    void deleteCredit(Long creditId);
    List<CreditDTO> getCreditsByCustomer(Long customerId) throws CustomerNotFoundException;
    List<CreditDTO> getCreditsByStatus(CreditStatus status);
    List<CreditDTO> getCreditsBetweenDates(Date startDate, Date endDate);
    List<CreditDTO> getCreditsAboveAmount(Double amount);
    CreditDTO updateCreditStatus(Long creditId, CreditStatus status) throws CreditNotFoundException;

    // Specific credit type operations
    PersonalCreditDTO savePersonalCredit(PersonalCreditDTO creditDTO) throws CustomerNotFoundException;
    ProfessionalCreditDTO saveProfessionalCredit(ProfessionalCreditDTO creditDTO) throws CustomerNotFoundException;
    RealEstateCreditDTO saveRealEstateCredit(RealEstateCreditDTO creditDTO) throws CustomerNotFoundException;

    // Credit simulation
    CreditSimulationResponseDTO simulateCredit(CreditSimulationRequestDTO simulationRequest);
}