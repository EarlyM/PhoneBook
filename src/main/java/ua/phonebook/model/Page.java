package ua.phonebook.model;

import ua.phonebook.model.entity.Contact;

import java.util.List;

public class Page {
    private Integer pageCount;
    private Integer currentPage;
    private List<Contact> contactsList;

    public Page() {
    }

    public Page(Integer pageCount, Integer currentPage, List<Contact> contactsList) {
        this.pageCount = pageCount;
        this.contactsList = contactsList;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public List<Contact> getContactsList() {
        return contactsList;
    }

    public void setContactsList(List<Contact> contactsList) {
        this.contactsList = contactsList;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
}
