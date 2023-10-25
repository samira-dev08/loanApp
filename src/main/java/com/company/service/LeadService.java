package com.company.service;

import com.company.domain.Customer;
import com.company.domain.TransactionDetails;
import com.company.enums.ActionStatus;
import com.company.enums.FinalStatus;
import com.company.enums.LeadStatus;
import com.company.repository.CustomerRepository;
import com.company.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
@RequiredArgsConstructor
@Service
public class LeadService {
    private final CustomerRepository customerRepository;
   private final TransactionRepository transactionRepository;
    public void identityStatus(Long id, LeadStatus status, String rejectReason) {
        Customer customer = customerRepository.getCustomerById(id);
        Long transactionId = customer.getTransactionDetailsId();
        TransactionDetails transactionDetails = transactionRepository.getTransactionById(transactionId);
        if (status != LeadStatus.APPROVE && Objects.isNull(rejectReason)) {
            throw new RuntimeException("Reject reason can not be empty");
        } else {
            if (status == LeadStatus.APPROVE) {
                transactionDetails.setActionStatus(ActionStatus.IDENTITY_CHECK_APPROVED.toString());
            } else {
                transactionDetails.setRejectReason(rejectReason);
            }
        }
        transactionRepository.updateStatus(transactionDetails);
    }
    public void initialStatus(Long id, LeadStatus status, String rejectReason) {
        Customer customer = customerRepository.getCustomerById(id);
        Long transactionId = customer.getTransactionDetailsId();
        TransactionDetails transactionDetails = transactionRepository.getTransactionById(transactionId);
        if (status != LeadStatus.APPROVE && Objects.isNull(rejectReason)) {
            throw new RuntimeException("Reject reason can not be empty");
        } else {
            if (status == LeadStatus.APPROVE) {
                transactionDetails.setActionStatus(ActionStatus.INITIAL_CHECK_APPROVED.toString());
            } else {
                transactionDetails.setRejectReason(rejectReason);
            }
            }
        transactionRepository.updateStatus(transactionDetails);
        }

    public void finalStatus(Long id, LeadStatus status, String rejectReason) {
        Customer customer = customerRepository.getCustomerById(id);
        Long transactionId = customer.getTransactionDetailsId();
        TransactionDetails transactionDetails = transactionRepository.getTransactionById(transactionId);
        if (status != LeadStatus.APPROVE && Objects.isNull(rejectReason)) {
            throw new RuntimeException("Reject reason can not be empty");
        } else {
            if (status == LeadStatus.APPROVE) {
                transactionDetails.setActionStatus(ActionStatus.FINAL_CHECK_APPROVED.toString());
                transactionDetails.setFinalStatus(FinalStatus.COMPLETED.toString());
            } else {
                transactionDetails.setRejectReason(rejectReason);
            }
        }
        transactionRepository.updateStatus(transactionDetails);
    }

    }

