package ua.phonebook.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import ua.phonebook.dao.UserDAO;
import ua.phonebook.model.entity.User;
import ua.phonebook.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final String USER_ROLE = "ROLE_USER";

    @Autowired
    private UserDAO userDAO;


    @Override
    public User createUser(User user, BindingResult bindingResult) {
        User checkUser = userDAO.findUserByName(user.getUsername());
        if(checkUser != null){
            bindingResult.rejectValue("username", "username", "Пользователь с таким именем уже существует");
            return null;
        }
        user.setRole(USER_ROLE);
        userDAO.createUser(user);
        return user;
    }
}
