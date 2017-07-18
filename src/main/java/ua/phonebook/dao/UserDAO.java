package ua.phonebook.dao;

import ua.phonebook.model.entity.User;


public interface UserDAO {
    User findUserByName(String name);
    void createUser(User user);
}
