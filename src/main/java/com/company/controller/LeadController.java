package com.company.controller;

import com.company.enums.LeadStatus;
import com.company.service.CustomerService;
import com.company.service.LeadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lead")
@RequiredArgsConstructor
public class LeadController {
    private final CustomerService customerService;
    private final LeadService leadService;

    @PostMapping("/identity-status/{id}")
    public void identityStatus(@PathVariable Long id,
                               @RequestParam LeadStatus status,
                               @RequestParam(required = false) String rejectReason) {
        customerService.updateIdentityCheckStatus(id, status, rejectReason);
    }

    @PostMapping("/initial-status/{id}")
    public void initialStatus(@PathVariable Long id,
                              @RequestParam LeadStatus status,
                              @RequestParam(required = false) String rejectReason) {
        leadService.initialStatus(id, status, rejectReason);

    }

    @PostMapping("/final-status/{id}")
    public void finalStatus(@PathVariable Long id,
                            @RequestParam LeadStatus status,
                            @RequestParam(required = false) String rejectReason) {
        leadService.finalStatus(id, status, rejectReason);
    }
}
