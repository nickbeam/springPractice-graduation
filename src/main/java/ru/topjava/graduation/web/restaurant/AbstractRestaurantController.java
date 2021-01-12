package ru.topjava.graduation.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.topjava.graduation.model.Restaurant;
import ru.topjava.graduation.service.RestaurantService;

import java.util.List;

import static ru.topjava.graduation.util.ValidationUtil.assureIdConsistent;
import static ru.topjava.graduation.util.ValidationUtil.checkNew;

public abstract class AbstractRestaurantController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService service;

    public Restaurant create(Restaurant restaurant) {
        checkNew(restaurant);
        log.info("create {}", restaurant);
        return service.create(restaurant);
    }

    public void update(Restaurant restaurant, int id) {
        assureIdConsistent(restaurant, id);
        log.info("update {} with id = {}", restaurant, id);
        service.update(restaurant);
    }

    public void delete(int id) {
        log.info("delete restaurant with id = {}", id);
        service.delete(id);
    }

    public Restaurant get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public List<Restaurant> getAll() {
        log.info("getAll restaurant");
        return service.getAll();
    }

}
