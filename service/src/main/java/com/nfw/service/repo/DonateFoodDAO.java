package com.nfw.service.repo;

import com.nfw.service.model.DonateFood;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

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

    public List<DonateFood>     findAll() {
        return list(namedQuery("donate.findAll"));
    }

}
