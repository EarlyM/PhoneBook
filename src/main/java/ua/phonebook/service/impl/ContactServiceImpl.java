package ua.phonebook.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.phonebook.dao.ContactDAO;
import ua.phonebook.model.Page;
import ua.phonebook.model.entity.Contact;
import ua.phonebook.service.ContactService;

import java.util.List;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactDAO contactDAO;


    @Override
    public Contact getContactById(Long id) {
        return contactDAO.findById(id);
    }

    @Override
    public void updateContact(Contact contact) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        contact.setUsername(name);
        contactDAO.update(contact);
    }

    @Override
    public void createContact(Contact contact) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        contact.setUsername(name);
        contactDAO.create(contact);
    }

    @Override
    public void deleteContact(Long id) {
        contactDAO.delete(id);
    }

    @Override
    public Page getPage(Integer pageNumber, Integer row, String username, String search, String field) {
        Long contactCount = contactDAO.getContactCount(username, search, field);
        int pageCount = (int) (contactCount%row == 0 ? contactCount/row : contactCount/row+1);

        int from = pageNumber * row - row;
        int to = pageNumber * row;

        List<Contact> contacts = contactDAO.getContactsByCriterion(from, to, username, search, field);

        return new Page(pageCount, pageNumber, contacts);
    }
}
