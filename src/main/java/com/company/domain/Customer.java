package com.company.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private Date birthDate;
    private String gender;
    private String passportNumber;
    private Long contactId;
    private Long addressId;
    private Long transactionDetailsId;
}
