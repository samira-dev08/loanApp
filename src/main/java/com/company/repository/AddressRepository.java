package com.company.repository;

import com.company.domain.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AddressRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    public Address save(Address address) {
        var keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO ADDRESS(country,city,street,postal_code) " +
                "VALUES(:country,:city,:street,:postal_code)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("country", address.getCountry());
        params.addValue("city", address.getCity());
        params.addValue("street", address.getStreet());
        params.addValue("postal_code", address.getPostalCode());

         jdbcTemplate.update(sql, params,keyHolder);
         address.setId(keyHolder.getKey().longValue());

        return address;
    }
}
