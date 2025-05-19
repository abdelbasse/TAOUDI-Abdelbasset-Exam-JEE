package com.example.exam_final_jee.web;

import com.example.exam_final_jee.dtos.*;
import com.example.exam_final_jee.enums.CreditStatus;
import com.example.exam_final_jee.exceptions.CreditNotFoundException;
import com.example.exam_final_jee.exceptions.CustomerNotFoundException;
import com.example.exam_final_jee.services.CreditService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/credits")
@AllArgsConstructor
@CrossOrigin("*")
public class CreditController {
    private CreditService creditService;

    @GetMapping
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public List<CreditDTO> credits() {
        return creditService.listCredits();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public CreditDTO getCredit(@PathVariable Long id) throws CreditNotFoundException {
        return creditService.getCredit(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCredit(@PathVariable Long id) {
        creditService.deleteCredit(id);
    }

    @GetMapping("/customer/{customerId}")
    @PreAuthorize("#customerId == authentication.principal.id or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public List<CreditDTO> getCustomerCredits(@PathVariable Long customerId) throws CustomerNotFoundException {
        return creditService.getCreditsByCustomer(customerId);
    }

    @GetMapping("/status/{status}")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public List<CreditDTO> getCreditsByStatus(@PathVariable CreditStatus status) {
        return creditService.getCreditsByStatus(status);
    }

    @GetMapping("/between-dates")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public List<CreditDTO> getCreditsBetweenDates(@RequestParam Date startDate, @RequestParam Date endDate) {
        return creditService.getCreditsBetweenDates(startDate, endDate);
    }

    @GetMapping("/above-amount")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public List<CreditDTO> getCreditsAboveAmount(@RequestParam Double amount) {
        return creditService.getCreditsAboveAmount(amount);
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public CreditDTO updateCreditStatus(@PathVariable Long id, @RequestParam CreditStatus status) throws CreditNotFoundException {
        return creditService.updateCreditStatus(id, status);
    }

    @PostMapping("/personal")
    @PreAuthorize("hasRole('CLIENT')")
    public PersonalCreditDTO savePersonalCredit(@RequestBody PersonalCreditDTO creditDTO) throws CustomerNotFoundException {
        return creditService.savePersonalCredit(creditDTO);
    }

    @PostMapping("/professional")
    @PreAuthorize("hasRole('CLIENT')")
    public ProfessionalCreditDTO saveProfessionalCredit(@RequestBody ProfessionalCreditDTO creditDTO) throws CustomerNotFoundException {
        return creditService.saveProfessionalCredit(creditDTO);
    }

    @PostMapping("/real-estate")
    @PreAuthorize("hasRole('CLIENT')")
    public RealEstateCreditDTO saveRealEstateCredit(@RequestBody RealEstateCreditDTO creditDTO) throws CustomerNotFoundException {
        return creditService.saveRealEstateCredit(creditDTO);
    }

    @PostMapping("/simulate")
    public CreditSimulationResponseDTO simulateCredit(@RequestBody CreditSimulationRequestDTO simulationRequest) {
        return creditService.simulateCredit(simulationRequest);
    }
}