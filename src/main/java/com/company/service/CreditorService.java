package com.company.service;

import com.company.domain.*;
import com.company.dto.request.LoanInfoRequest;
import com.company.dto.request.PersonalInfoRequest;
import com.company.enums.ActionStatus;
import com.company.mapper.AddressMapper;
import com.company.mapper.ContactMapper;
import com.company.mapper.LoanMapper;
import com.company.repository.AddressRepository;
import com.company.repository.ContactRepository;
import com.company.repository.CustomerRepository;
import com.company.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditorService {
    private final CustomerRepository customerRepository;
    private final AddressMapper addressMapper;
    private final ContactMapper contactMapper;
    private final LoanMapper loanMapper;
    private final TransactionRepository transactionRepository;
    private final AddressRepository addressRepository;
    private final ContactRepository contactRepository;
    private final ProductService productService;
    private final LoanService loanService;

    public void initialApprove(Long customerId, PersonalInfoRequest personalInfo) {
        Customer customer = customerRepository.getCustomerById(customerId);
        System.out.println(customer);
        TransactionDetails transactionDetails = transactionRepository.getTransactionById(customer.getTransactionDetailsId());
        if (!(transactionDetails.getActionStatus().equals(ActionStatus.IDENTITY_CHECK_APPROVED.toString()))) {
            throw new RuntimeException("Identity check failed!");
        }
        transactionDetails.setActionStatus(ActionStatus.WAITING_FOR_INITIAL_APPROVE.toString());
        transactionRepository.updateStatus(transactionDetails);
        Address address = addressMapper.toAddress(personalInfo.getAddress());
        Contact contact = contactMapper.toContact(personalInfo.getContact());
        Long addressId = addressRepository.save(address).getId();
        Long contactId = contactRepository.save(contact).getId();
        customer.setAddressId(addressId);
        customer.setContactId(contactId);
        customerRepository.update(customer);
    }

    public void finalApprove(Long customerId, LoanInfoRequest loanInfo) {

        Customer customer = customerRepository.getCustomerById(customerId);
        TransactionDetails transactionDetails = transactionRepository.getTransactionById(customer.getTransactionDetailsId());
        if (transactionDetails.getActionStatus() != ActionStatus.INITIAL_CHECK_APPROVED.toString()) {
            throw new RuntimeException("Initial check failed!");
        }
        Loan loan = loanMapper.toLoan(loanInfo);
        Long loanId = loanService.create(customerId, loan.getInterestRate());
        for (Product product : loan.getProductList()) {
            product.setLoanId(loanId);
            productService.create(product);
        }
        loanService.addTotalAndPreAmountToLoan(loanId);

        transactionDetails.setActionStatus(ActionStatus.WAITING_FOR_FINAL_APPROVE.toString());
        transactionRepository.updateStatus(transactionDetails);

    }
}
