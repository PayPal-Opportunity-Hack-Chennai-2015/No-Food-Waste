package com.nfw.service.repo;

import com.nfw.service.model.DonateFood;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class DonateFoodDAO extends AbstractDAO<DonateFood> {
    public DonateFoodDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    public DonateFood findById(Long id) {
        return get(id);
    }
    public long create(DonateFood donateFood) {
        return persist(donateFood).getId();
    }
}
