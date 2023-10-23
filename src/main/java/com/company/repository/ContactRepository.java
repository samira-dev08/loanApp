package com.company.repository;

import com.company.domain.Address;
import com.company.domain.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ContactRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public Contact save(Contact contact) {
        var keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO CONTACT(home_number,work_number,mobile,email) " +
                "VALUES(:home_number,:work_number,:mobile,:email)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("home_number", contact.getHomeNumber());
        params.addValue("work_number", contact.getWorkNumber());
        params.addValue("mobile", contact.getMobile());
        params.addValue("email", contact.getEmail());

        jdbcTemplate.update(sql, params, keyHolder);
        contact.setId(keyHolder.getKey().longValue());
        return contact;
    }
}
