package com.company.dto.request;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonalInfoRequest {
    private ContactRequest contact;
    private AddressRequest address;
}
