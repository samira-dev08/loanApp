package com.company.repository;

import com.company.domain.Customer;
import com.company.rowmapper.CustomerRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public Customer savePassInfo(Customer customer) {
         var keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO CUSTOMER(name,surname,patronymic,birth_date,gender,passport_number,transaction_details_id) " +
                "VALUES(:name,:surname,:patronymic,:birthDate,:gender,:passNumber,:transactionDetailsId)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", customer.getName());
        params.addValue("surname", customer.getSurname());
        params.addValue("patronymic", customer.getPatronymic());
        params.addValue("birthDate", customer.getBirthDate());
        params.addValue("gender", customer.getGender());
        params.addValue("passNumber", customer.getPassportNumber());
        params.addValue("transactionDetailsId", customer.getTransactionDetailsId());
        jdbcTemplate.update(sql, params,keyHolder);
       customer.setId(keyHolder.getKey().longValue());

        return customer;
    }

    public Customer getCustomerById(Long id) {
        String sql = "SELECT * FROM CUSTOMER WHERE id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        Customer customer = jdbcTemplate.queryForObject(sql, params, new CustomerRowMapper());
        return customer;
    }
    public void update(Customer customer) {
        String sql = "UPDATE CUSTOMER SET " +
                "name = :name," +
                "surname = :surname, " +
                "patronymic = :patronymic, " +
                "birth_date = :birthDate, " +
                "gender = :gender, " +
                "passport_number = :passportNumber, " +
                "contact_id = :contactId, " +
                "address_id=:addressId," +
                "transaction_details_id= :transactionDetailsId " +
                " WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", customer.getId());

        params.addValue("name", customer.getName());
        params.addValue("surname", customer.getSurname());
        params.addValue("patronymic", customer.getPatronymic());
        params.addValue("birthDate", customer.getBirthDate());
        params.addValue("gender", customer.getGender());
        params.addValue("passportNumber", customer.getPassportNumber());
        params.addValue("contactId", customer.getContactId());
        params.addValue("addressId", customer.getAddressId());
        params.addValue("transactionDetailsId", customer.getTransactionDetailsId());

        jdbcTemplate.update(sql, params);
    }

}
