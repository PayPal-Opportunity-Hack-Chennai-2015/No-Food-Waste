package com.nfw.service.repo;

import com.nfw.service.model.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDAO extends AbstractDAO<User> {
    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public User findById(Long id) {
        return get(id);
    }

    public long create(User User) {
        return persist(User).getId();
    }

    public List<User> findAll() {
        return list(namedQuery("findAll"));
    }
}