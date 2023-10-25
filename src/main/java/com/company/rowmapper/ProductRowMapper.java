package com.company.rowmapper;

import com.company.domain.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Product.builder()
                .price(rs.getBigDecimal("price"))
                .name(rs.getString("name"))
                .build();
    }
}
