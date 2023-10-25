package com.company.mapper;

import com.company.domain.Loan;
import com.company.domain.Product;
import com.company.dto.request.LoanInfoRequest;
import com.company.dto.request.ProductRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductRequest productRequest);
}
