package ua.phonebook.service;

import ua.phonebook.model.Page;
import ua.phonebook.model.entity.Contact;

public interface ContactService {
    Contact getContactById(Long id);
    void updateContact(Contact contact);
    void createContact(Contact contact);
    void deleteContact(Long id);

    Page getPage(Integer pageNumber, Integer row, String username, String search, String field);
}
