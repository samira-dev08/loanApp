package com.company.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JDBCTemplateConfig {
    @Bean
    public NamedParameterJdbcTemplate getJDBCTemplate(DataSource ds){
        return  new NamedParameterJdbcTemplate(ds);
    }
}
