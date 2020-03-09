package ru.topjava.graduation.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.topjava.graduation.DishTestData;
import ru.topjava.graduation.model.Dish;
import ru.topjava.graduation.util.exception.NotFoundException;


import static ru.topjava.graduation.DishTestData.*;
import static ru.topjava.graduation.RestaurantTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class DishServiceTest extends AbstractServiceTest {

    @Autowired
    private DishService service;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void create() {
        Dish newDish = DishTestData.getCreated();
        Dish created = service.create(newDish, REST_CHILL_ID);
        newDish.setId(created.getId());
        assertMatch(newDish, created);
        assertMatch(service.getAll(REST_CHILL_ID), newDish, DISH04, DISH06, DISH05);
    }

    @Test
    public void update() {
        Dish dish = DishTestData.getUpdated();
        service.update(dish, REST_BRILL_ID);
        assertMatch(service.get(DISH01_ID, REST_BRILL_ID), dish);
    }

    @Test
    public void updateNotFound() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + DISH09_ID);
        service.update(DISH09, REST_BRILL_ID);
    }

    @Test
    public void delete() {
        service.delete(DISH01_ID, REST_BRILL_ID);
        assertMatch(service.getAll(REST_BRILL_ID), DISH02, DISH03);
    }

    @Test
    public void deleteNotFound() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + DISH_WRONG_ID);
        service.delete(DISH_WRONG_ID, REST_BRILL_ID);
    }

    @Test
    public void get() {
        Dish dish = service.get(DISH01_ID, REST_BRILL_ID);
        assertMatch(dish, DISH01);
    }

    @Test
    public void getNotFound() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + DISH_WRONG_ID);
        service.get(DISH_WRONG_ID, REST_BRILL_ID);
    }

    @Test
    public void getAll() {
        assertMatch(service.getAll(REST_CHILL_ID), DISH04, DISH06, DISH05);
    }
}