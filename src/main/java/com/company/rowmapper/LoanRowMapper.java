package com.company.rowmapper;

import com.company.domain.Loan;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class LoanRowMapper implements RowMapper<Loan> {
    @Override
    public Loan mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Loan.builder()
                .id(rs.getLong("id"))
                .totalAmount(rs.getBigDecimal("total_amount"))
                .preAmount(rs.getBigDecimal("pre_amount"))
                .interestRate(rs.getBigDecimal("interest_rate"))
                .build();
    }
}
