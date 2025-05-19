package com.example.exam_final_jee.web;

import com.example.exam_final_jee.dtos.RepaymentDTO;
import com.example.exam_final_jee.enums.RepaymentType;
import com.example.exam_final_jee.exceptions.CreditNotFoundException;
import com.example.exam_final_jee.exceptions.RepaymentNotFoundException;
import com.example.exam_final_jee.services.RepaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/repayments")
@AllArgsConstructor
@CrossOrigin("*")
public class RepaymentController {
    private RepaymentService repaymentService;

    @PostMapping
    public RepaymentDTO saveRepayment(@RequestBody RepaymentDTO repaymentDTO) throws CreditNotFoundException {
        return repaymentService.saveRepayment(repaymentDTO);
    }

    @GetMapping("/credit/{creditId}")
    public List<RepaymentDTO> getCreditRepayments(@PathVariable Long creditId) {
        return repaymentService.getRepaymentsByCredit(creditId);
    }

    @GetMapping("/type/{type}")
    public List<RepaymentDTO> getRepaymentsByType(@PathVariable RepaymentType type) {
        return repaymentService.getRepaymentsByType(type);
    }

    @GetMapping("/between-dates")
    public List<RepaymentDTO> getRepaymentsBetweenDates(@RequestParam Date startDate, @RequestParam Date endDate) {
        return repaymentService.getRepaymentsBetweenDates(startDate, endDate);
    }

    @GetMapping("/total-repaid/{creditId}")
    public Double getTotalRepaidAmount(@PathVariable Long creditId) {
        return repaymentService.getTotalRepaidAmount(creditId);
    }

    @GetMapping("/{id}")
    public RepaymentDTO getRepayment(@PathVariable Long id) throws RepaymentNotFoundException {
        return repaymentService.getRepayment(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRepayment(@PathVariable Long id) {
        repaymentService.deleteRepayment(id);
    }
}