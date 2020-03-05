package ru.topjava.graduation.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.graduation.model.Dish;
import ru.topjava.graduation.model.Restaurant;
import ru.topjava.graduation.repository.DishRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaDishRepository implements DishRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Dish save(Dish dish, int restaurantId) {
        if (dish.isNew() || dish.getRestaurant() == null) {
            dish.setRestaurant(em.getReference(Restaurant.class, restaurantId));
        }
        if (dish.isNew()) {
            em.persist(dish);
            return dish;
        } else {
            return get(dish.getId(), restaurantId) == null ? null : em.merge(dish);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int restaurantId) {
        return em.createNamedQuery(Dish.DELETE)
                .setParameter("restaurantId", restaurantId)
                .executeUpdate() != 0;
    }

    @Override
    public Dish get(int id, int restaurantId) {
        return DataAccessUtils.singleResult(em.createNamedQuery(Dish.GET, Dish.class)
                .setParameter("id", id)
                .setParameter("restaurantId", restaurantId)
                .getResultList());
    }

    @Override
    public List<Dish> getAll(int restaurantId, Date date) {
        return em.createNamedQuery(Dish.ALL_BY_RESTAURANT, Dish.class)
                .setParameter("restaurantId", restaurantId)
                .setParameter("date", date)
                .getResultList();
    }
}
