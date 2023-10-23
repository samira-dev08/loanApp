package com.company.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassportInfoRequest {
    private String name;
    private String surname;
    private String patronymic;
    private Date birthDate;
    private String gender;
    private String passportNumber;
}
