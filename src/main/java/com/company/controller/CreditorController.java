package com.company.controller;

import com.company.domain.Customer;
import com.company.dto.request.LoanInfoRequest;
import com.company.dto.request.PassportInfoRequest;
import com.company.dto.request.PersonalInfoRequest;
import com.company.service.CreditorService;
import com.company.service.CustomerService;
import com.company.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/creditor")
@RequiredArgsConstructor
public class CreditorController {
    private final CreditorService creditorService;

    @PostMapping("/check-identity")
    public ResponseEntity<String> checkIdentity(@RequestBody PassportInfoRequest passInfo) {
        creditorService.checkIdentity(passInfo);
        return ResponseEntity.ok("Identity checked successfully.");
    }

    @PostMapping("/initial-approve/{id}")
    public ResponseEntity<String> initialApprove(@PathVariable("id") Long customerId, @RequestBody PersonalInfoRequest personalInfo) {
        creditorService.initialApprove(customerId,personalInfo);
        return ResponseEntity.ok("Initial approve completed");
    }

    @PostMapping("/final-approve/{customerId}")
    public ResponseEntity<String> finalApprove(@PathVariable Long customerId, @RequestBody LoanInfoRequest loanInfo) {
        creditorService.finalApprove(customerId,loanInfo);
        return ResponseEntity.ok("Final approve completed.");
    }
}
