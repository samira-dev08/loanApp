package com.company.repository;

import com.company.domain.Customer;
import com.company.domain.TransactionDetails;
import com.company.rowmapper.TransactionRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class TransactionRepository {
    private final NamedParameterJdbcTemplate jdbc;
    private final TransactionRowMapper transactionRowMapper;

    public TransactionDetails create(TransactionDetails transaction) {
        String sql = "INSERT INTO transaction_details (action_status, final_status) " +
                "VALUES (:actionStatus, :finalStatus)";
        var keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("actionStatus", transaction.getActionStatus());
        params.addValue("finalStatus", transaction.getFinalStatus());
        jdbc.update(sql, params, keyHolder);
        transaction.setId(keyHolder.getKey().longValue());
        return transaction;
    }

    public TransactionDetails getTransactionById(Long id) {
        String sql = "SELECT * FROM TRANSACTION_DETAILS WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject(
                sql,
                params,
                transactionRowMapper
        );
    }

    public void updateStatus(TransactionDetails transactionDetails) {
        String sql = "UPDATE transaction_details SET" +
                " action_status =:actionStatus,final_status =:finalStatus, reject_reason = :rejectReason" +
                " WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id",transactionDetails.getId());
        params.addValue("actionStatus", transactionDetails.getActionStatus());
        params.addValue("finalStatus", transactionDetails.getFinalStatus());
        params.addValue("rejectReason", transactionDetails.getRejectReason());
        jdbc.update(sql, params);


    }
}
