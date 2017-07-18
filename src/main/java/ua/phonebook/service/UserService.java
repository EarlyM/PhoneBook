package ua.phonebook.service;

import org.springframework.validation.BindingResult;
import ua.phonebook.model.entity.User;

public interface UserService {
    User createUser(User user, BindingResult bindingResult);
}
