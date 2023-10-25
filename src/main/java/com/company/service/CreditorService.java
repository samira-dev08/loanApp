package com.company.service;

import com.company.domain.*;
import com.company.dto.request.LoanInfoRequest;
import com.company.dto.request.PassportInfoRequest;
import com.company.dto.request.PersonalInfoRequest;
import com.company.dto.request.ProductRequest;
import com.company.enums.ActionStatus;
import com.company.enums.FinalStatus;
import com.company.exception.IdentityException;
import com.company.exception.InitialException;
import com.company.mapper.*;
import com.company.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CreditorService {
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;
    private final ProductService productService;
    private final LoanService loanService;
    private final CustomerMapper customerMapper;
    private final AddressService addressService;
    private final ContactService contactService;
    private final LoanRepository loanRepository;

    private static final Double PRE_AMOUNT_PERCENT = 40.0;
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

    public void initialApprove(Long customerId, PersonalInfoRequest personalInfo) {
        Customer customer = customerRepository.getCustomerById(customerId);
        System.out.println(customer);
        TransactionDetails transactionDetails = transactionRepository.getTransactionById(customer.getTransactionDetailsId());
        if (!(transactionDetails.getActionStatus().equals(ActionStatus.IDENTITY_CHECK_APPROVED.toString()))) {
            throw new IdentityException("Identity check failed!");
        }
        transactionDetails.setActionStatus(ActionStatus.WAITING_FOR_INITIAL_APPROVE.toString());
        transactionRepository.updateStatus(transactionDetails);
        Long addressId=addressService.saveAdrress(personalInfo.getAddress());
        Long contactId=contactService.saveContact(personalInfo.getContact());
        customer.setAddressId(addressId);
        customer.setContactId(contactId);
        customerRepository.update(customer);
    }

    public void finalApprove(Long customerId, LoanInfoRequest loanInfo) {

        Customer customer = customerRepository.getCustomerById(customerId);
        TransactionDetails transactionDetails = transactionRepository.getTransactionById(customer.getTransactionDetailsId());
        if (!(transactionDetails.getActionStatus().equals(ActionStatus.INITIAL_CHECK_APPROVED.toString()))) {
            throw new InitialException("Initial check failed!");
        }
        Loan loan = loanService.create(customerId, loanInfo.getInterestRate());
        BigDecimal totalAmount=productService.create(loan.getId(),loanInfo.getProductList());
        BigDecimal preAmount = totalAmount.multiply(BigDecimal.valueOf(PRE_AMOUNT_PERCENT / 100));
        loan.setTotalAmount(totalAmount);
        loan.setPreAmount(preAmount);
        loanRepository.update(loan);

        transactionDetails.setActionStatus(ActionStatus.WAITING_FOR_FINAL_APPROVE.toString());
        transactionRepository.updateStatus(transactionDetails);

    }
}
