package com.company.rowmapper;

import com.company.domain.Customer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Customer.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .surname(rs.getString("surname"))
                .patronymic(rs.getString("patronymic"))
                .birthDate(rs.getDate("birth_date"))
                .gender(rs.getString("gender"))
                .passportNumber(rs.getString("passport_number"))
                .contactId(rs.getLong("contact_id")).addressId(rs.getLong("address_id"))
                .transactionDetailsId(rs.getLong("transaction_details_id"))
                .build();
    }
}
