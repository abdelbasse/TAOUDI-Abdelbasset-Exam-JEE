package com.example.exam_final_jee.mappers;

import com.example.exam_final_jee.dtos.*;
import com.example.exam_final_jee.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankMapper {
    public CustomerDTO fromCustomer(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    public Customer fromCustomerDTO(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    public CreditDTO fromCredit(Credit credit) {
        CreditDTO creditDTO = new CreditDTO();
        BeanUtils.copyProperties(credit, creditDTO);
        creditDTO.setCustomerId(credit.getCustomer().getId());
        return creditDTO;
    }

    public PersonalCreditDTO fromPersonalCredit(PersonalCredit credit) {
        PersonalCreditDTO creditDTO = new PersonalCreditDTO();
        BeanUtils.copyProperties(credit, creditDTO);
        creditDTO.setCustomerId(credit.getCustomer().getId());
        return creditDTO;
    }

    public ProfessionalCreditDTO fromProfessionalCredit(ProfessionalCredit credit) {
        ProfessionalCreditDTO creditDTO = new ProfessionalCreditDTO();
        BeanUtils.copyProperties(credit, creditDTO);
        creditDTO.setCustomerId(credit.getCustomer().getId());
        return creditDTO;
    }

    public RealEstateCreditDTO fromRealEstateCredit(RealEstateCredit credit) {
        RealEstateCreditDTO creditDTO = new RealEstateCreditDTO();
        BeanUtils.copyProperties(credit, creditDTO);
        creditDTO.setCustomerId(credit.getCustomer().getId());
        return creditDTO;
    }

    public PersonalCredit fromPersonalCreditDTO(PersonalCreditDTO creditDTO) {
        PersonalCredit credit = new PersonalCredit();
        BeanUtils.copyProperties(creditDTO, credit);
        return credit;
    }

    public ProfessionalCredit fromProfessionalCreditDTO(ProfessionalCreditDTO creditDTO) {
        ProfessionalCredit credit = new ProfessionalCredit();
        BeanUtils.copyProperties(creditDTO, credit);
        return credit;
    }

    public RealEstateCredit fromRealEstateCreditDTO(RealEstateCreditDTO creditDTO) {
        RealEstateCredit credit = new RealEstateCredit();
        BeanUtils.copyProperties(creditDTO, credit);
        return credit;
    }

    public RepaymentDTO fromRepayment(Repayment repayment) {
        RepaymentDTO repaymentDTO = new RepaymentDTO();
        BeanUtils.copyProperties(repayment, repaymentDTO);
        repaymentDTO.setCreditId(repayment.getCredit().getId());
        return repaymentDTO;
    }

    public Repayment fromRepaymentDTO(RepaymentDTO repaymentDTO) {
        Repayment repayment = new Repayment();
        BeanUtils.copyProperties(repaymentDTO, repayment);
        return repayment;
    }
}