package com.company.service;

import com.company.domain.Customer;
import com.company.domain.TransactionDetails;
import com.company.dto.request.PassportInfoRequest;
import com.company.enums.ActionStatus;
import com.company.enums.FinalStatus;
import com.company.enums.LeadStatus;
import com.company.mapper.CustomerMapper;
import com.company.repository.CustomerRepository;
import com.company.repository.TransactionRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Data
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final TransactionRepository transactionRepository;

    public void checkIdentity(PassportInfoRequest passInfo) {
        Customer customer = customerMapper.toCustomer(passInfo);
        TransactionDetails transactionDetails = TransactionDetails.builder()
                .actionStatus(ActionStatus.WAITING_FOR_IDENTITY_APPROVE.toString())
                .finalStatus(FinalStatus.IN_PROGRESS.toString())
                .build();
        TransactionDetails transaction= transactionRepository.create(transactionDetails);
        customer.setTransactionDetailsId(transaction.getId());
        customerRepository.savePassInfo(customer);
    }

    public void updateIdentityCheckStatus(Long id, LeadStatus status, String rejectReason) {
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
}
