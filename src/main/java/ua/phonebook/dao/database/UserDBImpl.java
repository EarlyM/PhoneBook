package ua.phonebook.dao.database;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import ua.phonebook.dao.UserDAO;
import ua.phonebook.model.entity.User;

public class UserDBImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findUserByName(String name) {
        return (User) sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("username", name)).uniqueResult();
    }

    @Override
    public void createUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }
}
