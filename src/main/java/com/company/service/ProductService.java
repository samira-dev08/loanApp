package com.company.service;

import com.company.domain.Customer;
import com.company.domain.Product;
import com.company.repository.ProductRepository;
import com.company.rowmapper.CustomerRowMapper;
import com.company.rowmapper.ProductRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductRowMapper productRowMapper;
    NamedParameterJdbcTemplate jdbcTemplate;

    public Product create(Product product) {
      return   productRepository.save(product);
    }


    public List<Product> getByLoanIdProducts(Long loanId){
       return productRepository.getByLoanIdProducts(loanId);
    }
}
