package com.company.service;

import com.company.domain.Loan;
import com.company.domain.Product;

import com.company.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LoanService {
    private final LoanRepository loanRepository;
    ProductService productService;
    private static final Double PRE_AMOUNT_PERCENT = 40.0;


    public Long create(Long customerId, BigDecimal interestRate) {
        Loan loan = new Loan();
        loan.setCustomerId(customerId);
        loan.setInterestRate(interestRate);
        return loanRepository.create(loan).getId();
    }

    @Transactional
    public void addTotalAndPreAmountToLoan(Long loanId) {
        Loan loan = loanRepository.getById(loanId);
        List<Product> products = productService.getByLoanIdProducts(loanId);
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (Product product : products) {
            totalAmount.add(product.getPrice());
        }
        BigDecimal preAmount = totalAmount.multiply(BigDecimal.valueOf(PRE_AMOUNT_PERCENT / 100));
        loan.setTotalAmount(totalAmount);
        loan.setPreAmount(preAmount);
        loanRepository.update(loan);

    }
}
