package com.example.exam_final_jee.mappers;

import com.example.exam_final_jee.dtos.*;
import com.example.exam_final_jee.entities.*;
import org.springframework.stereotype.Service;

@Service
public class CreditMapper {

    public CreditDTO fromCredit(Credit credit) {
        if (credit instanceof PersonalCredit) {
            PersonalCredit pc = (PersonalCredit) credit;
            PersonalCreditDTO dto = new PersonalCreditDTO();
            fillCommonFields(dto, pc);
            dto.setPurpose(pc.getPurpose());
            return dto;

        } else if (credit instanceof ProfessionalCredit) {
            ProfessionalCredit pc = (ProfessionalCredit) credit;
            ProfessionalCreditDTO dto = new ProfessionalCreditDTO();
            fillCommonFields(dto, pc);
            dto.setPurpose(pc.getPurpose());
            dto.setCompanyBusinessName(pc.getCompanyBusinessName());
            return dto;

        } else if (credit instanceof RealEstateCredit) {
            RealEstateCredit rc = (RealEstateCredit) credit;
            RealEstateCreditDTO dto = new RealEstateCreditDTO();
            fillCommonFields(dto, rc);
            dto.setPropertyType(rc.getPropertyType());
            return dto;

        } else {
            CreditDTO dto = new CreditDTO();
            fillCommonFields(dto, credit);
            return dto;
        }
    }

    private void fillCommonFields(CreditDTO dto, Credit credit) {
        dto.setId(credit.getId());
        dto.setAmount(credit.getAmount());
        dto.setApplicationDate(credit.getApplicationDate());
        dto.setRepaymentDuration(credit.getRepaymentDuration());
        dto.setInterestRate(credit.getInterestRate());
        dto.setStatus(credit.getStatus());
        dto.setAcceptanceDate(credit.getAcceptanceDate());
        if (credit.getCustomer() != null) {
            dto.setCustomerId(credit.getCustomer().getId());
        }
    }
}
