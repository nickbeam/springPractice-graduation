package ru.topjava.graduation.repository;

import ru.topjava.graduation.model.Dish;

import java.util.Date;
import java.util.List;

public interface DishRepository {
    Dish save(Dish dish, int restaurantId);

    boolean delete(int id, int restaurantId);

    Dish get(int id, int restaurantId);

    List<Dish> getAllByRestaurant(int restaurantId, Date date);

    List<Dish> getAllWithRestaurants(Date date);
}
