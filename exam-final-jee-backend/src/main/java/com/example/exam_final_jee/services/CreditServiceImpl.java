package com.example.exam_final_jee.services;

import com.example.exam_final_jee.dtos.*;
import com.example.exam_final_jee.entities.*;
import com.example.exam_final_jee.enums.CreditStatus;
import com.example.exam_final_jee.exceptions.CreditNotFoundException;
import com.example.exam_final_jee.exceptions.CustomerNotFoundException;
import com.example.exam_final_jee.mappers.BankMapper;
import com.example.exam_final_jee.repo.CreditRepository;
import com.example.exam_final_jee.repo.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class CreditServiceImpl implements CreditService {
    private CreditRepository creditRepository;
    private CustomerRepository customerRepository;
    private BankMapper bankMapper;

    @Override
    public CreditDTO getCredit(Long creditId) throws CreditNotFoundException {
        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new CreditNotFoundException("Credit Not Found"));
        return mapToSpecificCreditDTO(credit);
    }

    @Override
    public List<CreditDTO> listCredits() {
        List<Credit> credits = creditRepository.findAll();
        return credits.stream()
                .map(this::mapToSpecificCreditDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCredit(Long creditId) {
        creditRepository.deleteById(creditId);
    }

    @Override
    public List<CreditDTO> getCreditsByCustomer(Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
        List<Credit> credits = creditRepository.findByCustomerId(customerId);
        return credits.stream()
                .map(this::mapToSpecificCreditDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditDTO> getCreditsByStatus(CreditStatus status) {
        List<Credit> credits = creditRepository.findByStatus(status);
        return credits.stream()
                .map(this::mapToSpecificCreditDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditDTO> getCreditsBetweenDates(Date startDate, Date endDate) {
        List<Credit> credits = creditRepository.findCreditsBetweenDates(startDate, endDate);
        return credits.stream()
                .map(this::mapToSpecificCreditDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditDTO> getCreditsAboveAmount(Double amount) {
        List<Credit> credits = creditRepository.findByAmountGreaterThan(amount);
        return credits.stream()
                .map(this::mapToSpecificCreditDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CreditDTO updateCreditStatus(Long creditId, CreditStatus status) throws CreditNotFoundException {
        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new CreditNotFoundException("Credit Not Found"));
        credit.setStatus(status);
        if(status == CreditStatus.ACCEPTED) {
            credit.setAcceptanceDate(new Date());
        }
        Credit updatedCredit = creditRepository.save(credit);
        return mapToSpecificCreditDTO(updatedCredit);
    }

    @Override
    public PersonalCreditDTO savePersonalCredit(PersonalCreditDTO creditDTO) throws CustomerNotFoundException {
        PersonalCredit credit = bankMapper.fromPersonalCreditDTO(creditDTO);
        return saveCredit(credit, creditDTO.getCustomerId());
    }

    @Override
    public ProfessionalCreditDTO saveProfessionalCredit(ProfessionalCreditDTO creditDTO) throws CustomerNotFoundException {
        ProfessionalCredit credit = bankMapper.fromProfessionalCreditDTO(creditDTO);
        return saveCredit(credit, creditDTO.getCustomerId());
    }

    @Override
    public RealEstateCreditDTO saveRealEstateCredit(RealEstateCreditDTO creditDTO) throws CustomerNotFoundException {
        RealEstateCredit credit = bankMapper.fromRealEstateCreditDTO(creditDTO);
        return saveCredit(credit, creditDTO.getCustomerId());
    }

    @Override
    public CreditSimulationResponseDTO simulateCredit(CreditSimulationRequestDTO simulationRequest) {
        // Calculate monthly payment and total interest
        double monthlyInterestRate = simulationRequest.getInterestRate() / 100 / 12;
        int numberOfPayments = simulationRequest.getDurationMonths();

        double monthlyPayment = (simulationRequest.getAmount() * monthlyInterestRate) /
                (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments));

        double totalInterest = (monthlyPayment * numberOfPayments) - simulationRequest.getAmount();

        return new CreditSimulationResponseDTO(
                monthlyPayment,
                totalInterest,
                monthlyPayment * numberOfPayments
        );
    }

    private <T extends CreditDTO> T saveCredit(Credit credit, Long customerId) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found"));
        credit.setCustomer(customer);
        credit.setApplicationDate(new Date());
        credit.setStatus(CreditStatus.IN_PROGRESS);

        Credit savedCredit = creditRepository.save(credit);
        return (T) mapToSpecificCreditDTO(savedCredit);
    }

    private CreditDTO mapToSpecificCreditDTO(Credit credit) {
        if (credit instanceof PersonalCredit) {
            return bankMapper.fromPersonalCredit((PersonalCredit) credit);
        } else if (credit instanceof ProfessionalCredit) {
            return bankMapper.fromProfessionalCredit((ProfessionalCredit) credit);
        } else if (credit instanceof RealEstateCredit) {
            return bankMapper.fromRealEstateCredit((RealEstateCredit) credit);
        }
        return bankMapper.fromCredit(credit);
    }
}