package ru.topjava.graduation.web.dish;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.topjava.graduation.model.Dish;
import ru.topjava.graduation.web.user.AdminRestController;

import java.util.List;

@RestController
@RequestMapping(value = DishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController extends AbstractDishController {
    static final String REST_URL = "/rest/dishes";

    @Override
    public Dish create(Dish dish, int restaurantId) {
        return super.create(dish, restaurantId);
    }

    @Override
    public void update(Dish dish, int id, int restaurantId) {
        super.update(dish, id, restaurantId);
    }

    @Override
    public void delete(int id, int restaurantId) {
        super.delete(id, restaurantId);
    }

    @Override
    public Dish get(int id, int restaurantId) {
        return super.get(id, restaurantId);
    }

    @Override
    public List<Dish> getAll(int restaurantId) {
        return super.getAll(restaurantId);
    }
}
