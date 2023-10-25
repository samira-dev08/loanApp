package com.company.dto.request;

import com.company.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LoanInfoRequest {
    private BigDecimal interestRate;
    private List<ProductRequest> productList;
}
