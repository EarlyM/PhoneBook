package ua.phonebook.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.phonebook.dao.UserDAO;
import ua.phonebook.model.entity.User;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomUserAuthenticationService implements UserDetailsService{

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findUserByName(username);
        if(user == null){
            throw new UsernameNotFoundException("Пользователя " + username + "не найдено");
        }
        List<GrantedAuthority> grantList = new ArrayList<>();

        grantList.add(new SimpleGrantedAuthority(user.getRole()));

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantList);

        return userDetails;
    }
}
