package ru.topjava.graduation.repository;

import ru.topjava.graduation.model.Dish;

import java.util.Date;
import java.util.List;

public interface DishRepository {
    Dish save(Dish dish);

    boolean delete(int id);

    Dish get(int id);

    List<Dish> getAll(int restaurantId);

    List<Dish> getBetween(int restaurantId, Date startDate, Date endDate);
}
