package com.example.exam_final_jee.mappers;

import com.example.exam_final_jee.dtos.RepaymentDTO;
import com.example.exam_final_jee.entities.Repayment;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RepaymentMapper {
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