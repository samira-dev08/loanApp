package com.company.mapper;

import com.company.domain.Customer;
import com.company.dto.request.PassportInfoRequest;
import com.company.dto.response.CustomerResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomer(PassportInfoRequest passInfo);
    CustomerResponse toCustomerResponse(Customer customer);

}

