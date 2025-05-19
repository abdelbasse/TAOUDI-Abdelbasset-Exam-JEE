package com.example.exam_final_jee.services;

import com.example.exam_final_jee.dtos.RepaymentDTO;
import com.example.exam_final_jee.entities.Credit;
import com.example.exam_final_jee.entities.Repayment;
import com.example.exam_final_jee.enums.RepaymentType;
import com.example.exam_final_jee.exceptions.CreditNotFoundException;
import com.example.exam_final_jee.exceptions.RepaymentNotFoundException;
import com.example.exam_final_jee.mappers.BankMapper;
import com.example.exam_final_jee.repo.CreditRepository;
import com.example.exam_final_jee.repo.RepaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class RepaymentServiceImpl implements RepaymentService {
    private RepaymentRepository repaymentRepository;
    private CreditRepository creditRepository;
    private BankMapper bankMapper;

    @Override
    public RepaymentDTO saveRepayment(RepaymentDTO repaymentDTO) throws CreditNotFoundException {
        Credit credit = creditRepository.findById(repaymentDTO.getCreditId())
                .orElseThrow(() -> new CreditNotFoundException("Credit Not Found"));

        Repayment repayment = bankMapper.fromRepaymentDTO(repaymentDTO);
        repayment.setCredit(credit);
        repayment.setDate(new Date());

        Repayment savedRepayment = repaymentRepository.save(repayment);
        return bankMapper.fromRepayment(savedRepayment);
    }

    @Override
    public List<RepaymentDTO> getRepaymentsByCredit(Long creditId) {
        List<Repayment> repayments = repaymentRepository.findByCreditId(creditId);
        return repayments.stream()
                .map(repayment -> bankMapper.fromRepayment(repayment))
                .collect(Collectors.toList());
    }

    @Override
    public List<RepaymentDTO> getRepaymentsByType(RepaymentType type) {
        List<Repayment> repayments = repaymentRepository.findByType(type);
        return repayments.stream()
                .map(repayment -> bankMapper.fromRepayment(repayment))
                .collect(Collectors.toList());
    }

    @Override
    public List<RepaymentDTO> getRepaymentsBetweenDates(Date startDate, Date endDate) {
        List<Repayment> repayments = repaymentRepository.findRepaymentsBetweenDates(startDate, endDate);
        return repayments.stream()
                .map(repayment -> bankMapper.fromRepayment(repayment))
                .collect(Collectors.toList());
    }

    @Override
    public Double getTotalRepaidAmount(Long creditId) {
        return repaymentRepository.getTotalRepaidAmountByCreditId(creditId);
    }

    @Override
    public RepaymentDTO getRepayment(Long repaymentId) throws RepaymentNotFoundException {
        Repayment repayment = repaymentRepository.findById(repaymentId)
                .orElseThrow(() -> new RepaymentNotFoundException("Repayment Not Found"));
        return bankMapper.fromRepayment(repayment);
    }

    @Override
    public void deleteRepayment(Long repaymentId) {
        repaymentRepository.deleteById(repaymentId);
    }
}