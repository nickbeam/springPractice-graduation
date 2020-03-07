package ru.topjava.graduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.topjava.graduation.model.Dish;
import ru.topjava.graduation.repository.DishRepository;

import java.util.Date;
import java.util.List;

import static ru.topjava.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishService {

    private DishRepository repository;

    @Autowired
    public DishService(DishRepository repository) {
        this.repository = repository;
    }

    public Dish create(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        return repository.save(dish, restaurantId);
    }

    public void update(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        checkNotFoundWithId(repository.save(dish, restaurantId), dish.getId());
    }

    public void delete(int id, int restaurantId) {
        checkNotFoundWithId(repository.delete(id, restaurantId), id);
    }

    public Dish get(int id, int restaurantId) {
        return checkNotFoundWithId(repository.get(id, restaurantId), id);
    }

    public List<Dish> getAll(int restaurantId, Date date) {
        return repository.getAll(restaurantId, date);
    }
}
