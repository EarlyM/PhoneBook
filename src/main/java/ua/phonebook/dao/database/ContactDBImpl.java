package ua.phonebook.dao.database;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import ua.phonebook.dao.ContactDAO;
import ua.phonebook.model.entity.Contact;

import java.util.List;

public class ContactDBImpl implements ContactDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Contact findById(Long id) {
        return sessionFactory.getCurrentSession().get(Contact.class, id);
    }

    @Override
    public void update(Contact contact) {
        sessionFactory.getCurrentSession().update(contact);
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Contact contact = session.load(Contact.class, id);
        sessionFactory.getCurrentSession().delete(contact);
    }

    @Override
    public void create(Contact contact) {
        sessionFactory.getCurrentSession().save(contact);
    }

    @Override
    public Long getContactCount(String username, String search, String field) {
        Criteria criteria =  createCriteria(username, search, field);
        criteria.setProjection(Projections.rowCount());

        return (Long) criteria.uniqueResult();
    }

    @Override
    public List<Contact> getContactsByCriterion(int from, int to, String username, String search, String field) {
        Criteria criteria = createCriteria(username, search, field);
        criteria.setFirstResult(from).setMaxResults(to);
        return criteria.list();
    }

    private Criteria createCriteria(String username, String search, String field){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Contact.class);
        if(field != null && search != null){
            criteria.add(Restrictions.ilike(field, search, MatchMode.ANYWHERE));
            criteria.addOrder(Order.asc(field));
        }
        criteria.add(Restrictions.eq("username", username));
        return criteria;
    }
}
