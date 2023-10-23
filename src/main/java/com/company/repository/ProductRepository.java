package com.company.repository;

import com.company.domain.Address;
import com.company.domain.Loan;
import com.company.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ProductRepository {
    private final NamedParameterJdbcTemplate jdbc;

    public Product save(Product product) {
        String sql = "INSERT INTO PRODUCT(name,price,loan_id) " +
                "VALUES(:name,:price,:street,:loanId)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", product.getName());
        params.addValue("price", product.getPrice());
        params.addValue("loanId", product.getLoanId());
        product.setId((long) jdbc.update(sql, params));

        return product;
    }
    public List<Product> getByLoanIdProducts(Long loanId){
        String sql = "SELECT * FROM PRODUCT WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", loanId);
        return jdbc.query(sql, params, (rs, rowNum) -> {
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getBigDecimal("price"));
            return product;
        });
}
}
