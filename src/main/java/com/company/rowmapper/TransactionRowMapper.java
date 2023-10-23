package com.company.rowmapper;

import com.company.domain.Customer;
import com.company.domain.TransactionDetails;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class TransactionRowMapper implements RowMapper<TransactionDetails> {

    @Override
    public TransactionDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        return TransactionDetails.builder()
                .id(rs.getLong("id"))
                .actionStatus(rs.getString("action_status"))
                .finalStatus(rs.getString("final_status"))
                .rejectReason(rs.getString("reject_reason"))
                .build();

    }
}
