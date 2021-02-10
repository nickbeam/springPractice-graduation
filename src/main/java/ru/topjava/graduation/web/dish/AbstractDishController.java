package ru.topjava.graduation.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.topjava.graduation.model.Dish;
import ru.topjava.graduation.service.DishService;

import java.util.List;

import static ru.topjava.graduation.util.ValidationUtil.assureIdConsistent;
import static ru.topjava.graduation.util.ValidationUtil.checkNew;

public abstract class AbstractDishController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishService service;

    public Dish create(Dish dish, int restaurantId) {
        log.info("create dish {} with restaurant id = {}", dish, restaurantId);
        checkNew(dish);
        return service.create(dish, restaurantId);
    }

    public void update(Dish dish, int id, int restaurantId) {
        log.info("update dish {} with restaurant id = {}", dish, restaurantId);
        assureIdConsistent(dish, id);
        service.update(dish, restaurantId);
    }

    public void delete(int id, int restaurantId) {
        log.info("delete dish with id = {}", id);
        service.delete(id, restaurantId);
    }

    public Dish get(int id, int restaurantId) {
        log.info("get dish with id = {} and restaurant id = {}", id, restaurantId);
        return service.get(id, restaurantId);
    }

    public List<Dish> getAllByRestaurant(int restaurantId) {
        log.info("getAll dishes from restaurant with id = {}", restaurantId);
        return service.getAllByRestaurant(restaurantId);
    }

    public List<Dish> getAllWithRestaurants() {
        log.info("getAll dishes with restaurants for today");
        return service.getAllWithRestaurants();
    }
}
