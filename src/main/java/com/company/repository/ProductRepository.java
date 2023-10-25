package com.company.repository;

import com.company.domain.Address;
import com.company.domain.Loan;
import com.company.domain.Product;
import com.company.rowmapper.ProductRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ProductRepository {
    private final NamedParameterJdbcTemplate jdbc;

    public Product save(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO PRODUCT(name,price,loan_id) " +
                "VALUES(:name,:price,:loanId)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", product.getName());
        params.addValue("price", product.getPrice());
        params.addValue("loanId", product.getLoanId());

        jdbc.update(sql, params, keyHolder);
        product.setId(keyHolder.getKey().longValue());

        return product;
    }

    public List<Product> getByLoanIdProducts(Long loanId) {
        String sql = "SELECT * FROM PRODUCT WHERE loan_id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", loanId);
        return jdbc.query(sql, params,new ProductRowMapper());

    }
}
