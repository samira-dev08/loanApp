package com.company.mapper;

import com.company.domain.Contact;
import com.company.dto.request.ContactRequest;
import com.company.dto.response.ContactResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    Contact toContact(ContactRequest contact);
    ContactResponse toContactResponse(Contact contact);
}
