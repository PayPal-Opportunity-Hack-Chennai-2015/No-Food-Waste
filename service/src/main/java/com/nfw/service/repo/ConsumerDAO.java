package com.nfw.service.repo;

import com.nfw.service.model.FoodConsumer;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class ConsumerDAO extends AbstractDAO<FoodConsumer> {

    public ConsumerDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public FoodConsumer findById(Long id) {
        return get(id);
    }

    public long create(FoodConsumer foodConsumer) {
        return persist(foodConsumer).getId();
    }

    public List<FoodConsumer> findAll() {
        return list(namedQuery("consumer.findAll"));
    }

}
