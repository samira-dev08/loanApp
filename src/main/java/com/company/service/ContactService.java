package com.company.service;

import com.company.domain.Contact;
import com.company.dto.request.ContactRequest;
import com.company.mapper.ContactMapper;
import com.company.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactMapper contactMapper;
    private final ContactRepository contactRepository;
    public Long saveContact(ContactRequest contactRequest) {
        Contact contact = contactMapper.toContact(contactRequest);
       return contactRepository.save(contact).getId();
    }
}
