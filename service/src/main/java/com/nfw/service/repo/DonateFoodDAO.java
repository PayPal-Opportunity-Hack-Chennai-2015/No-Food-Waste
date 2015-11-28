package com.nfw.service.repo;

import com.nfw.service.models.DonateFood;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

/**
 * Created by sriram on 29/11/15.
 */
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
