package com.company.service;


import com.company.domain.Address;
import com.company.dto.request.AddressRequest;
import com.company.mapper.AddressMapper;
import com.company.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressMapper addressMapper;
    AddressRepository addressRepository;

public Long saveAdrress(AddressRequest adress){
    Address address = addressMapper.toAddress(adress);
    return addressRepository.save(address).getId();
}


}
