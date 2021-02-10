package ru.topjava.graduation.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.topjava.graduation.model.Dish;
import ru.topjava.graduation.repository.DishRepository;

import java.util.Date;
import java.util.List;

@Repository
public class DataJpaDishRepository implements DishRepository {

    @Autowired
    private CrudDishRepository crudRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Override
    public Dish save(Dish dish, int restaurantId) {
        if (!dish.isNew() && get(dish.getId(), restaurantId) == null) {
            return null;
        }
        dish.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudRepository.save(dish);
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return crudRepository.delete(id, restaurantId) != 0;
    }

    @Override
    public Dish get(int id, int restaurantId) {
        return crudRepository.get(id, restaurantId);
    }

    @Override
    public List<Dish> getAllByRestaurant(int restaurantId, Date date) {
        return crudRepository.getAllByRestaurant(restaurantId, date);
    }

    @Override
    public List<Dish> getAllWithRestaurants(Date date) {
        return crudRepository.getAllWithRestaurants(date);
    }
}
