package com.company.service;

import com.company.domain.Customer;
import com.company.domain.Product;
import com.company.dto.request.ProductRequest;
import com.company.mapper.ProductMapper;
import com.company.repository.ProductRepository;
import com.company.rowmapper.CustomerRowMapper;
import com.company.rowmapper.ProductRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    public BigDecimal create(Long loanId, List<ProductRequest> productRequest) {
        BigDecimal price=BigDecimal.ZERO;
        for (ProductRequest products : productRequest) {
            Product product=  productMapper.toProduct(products);
            product.setLoanId(loanId);
            productRepository.save(product);
            price.add(product.getPrice());
        }
      return  price ;
    }


    public List<Product> getByLoanIdProducts(Long loanId){
       return productRepository.getByLoanIdProducts(loanId);
    }
}
