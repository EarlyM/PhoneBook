package ua.phonebook.dao;

import ua.phonebook.model.entity.Contact;

import java.util.List;

public interface ContactDAO {

    String SURNAME = "surname";
    String NAME = "name";
    String MOBILE_PHONE = "mobilePhone";

    Contact findById(Long id);
    void update(Contact contact);
    void delete(Long id);
    void create(Contact contact);

    Long getContactCount(String username, String search, String field);

    List<Contact> getContactsByCriterion(int from, int to, String username, String search, String field);
}
