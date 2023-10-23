package com.company.mapper;

import com.company.domain.Address;
import com.company.dto.request.AddressRequest;
import com.company.dto.response.AddressResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toAddress(AddressRequest address);
    AddressResponse toAdrressResponse(Address address);
}

