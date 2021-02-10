package ru.topjava.graduation.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.graduation.model.Dish;
import ru.topjava.graduation.model.User;

import java.util.Date;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {
    @Transactional
    @Modifying
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    Dish get(@Param("id") int id, @Param("restaurantId") int restaurantId);

    List<Dish> getAllByRestaurant(@Param("restaurantId") int restaurantId, @Param("date") Date date);

    List<Dish> getAllWithRestaurants(@Param("date") Date date);
}
