package ua.phonebook.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {
    private long id;
    private String surname;
    private String name;
    private String patronymic;
    private String mobilePhone;
    private String homePhone;
    private String address;
    private String eMail;
    private String username;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "patronymic")
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Basic
    @Column(name = "mobile_phone")
    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Basic
    @Column(name = "home_phone")
    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "e_mail")
    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (id != contact.id) return false;
        if (surname != null ? !surname.equals(contact.surname) : contact.surname != null) return false;
        if (name != null ? !name.equals(contact.name) : contact.name != null) return false;
        if (patronymic != null ? !patronymic.equals(contact.patronymic) : contact.patronymic != null) return false;
        if (mobilePhone != null ? !mobilePhone.equals(contact.mobilePhone) : contact.mobilePhone != null)
            return false;
        if (homePhone != null ? !homePhone.equals(contact.homePhone) : contact.homePhone != null) return false;
        if (address != null ? !address.equals(contact.address) : contact.address != null) return false;
        if (eMail != null ? !eMail.equals(contact.eMail) : contact.eMail != null) return false;
        if (username != null ? !username.equals(contact.username) : contact.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (eMail != null ? eMail.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return id + "," +
               surname + "," +
               name + "," +
               patronymic + "," +
               mobilePhone + "," +
               (homePhone == null ? "" : homePhone) + "," +
               (address == null ? "" : address) + "," +
               (eMail == null ? "" : eMail) + "," +
               username;
    }
}
