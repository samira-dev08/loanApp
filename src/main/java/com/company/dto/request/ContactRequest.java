package com.company.dto.request;

import lombok.Data;

@Data
public class ContactRequest {
    private String homeNumber;
    private String workNumber;
    private String mobile;
    private String email;
}
