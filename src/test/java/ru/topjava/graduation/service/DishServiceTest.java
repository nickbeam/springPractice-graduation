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
import ru.topjava.graduation.model.Dish;

import java.util.Date;

import static org.junit.Assert.*;
import static ru.topjava.graduation.DishTestData.*;
import static ru.topjava.graduation.RestaurantTestData.REST_CHILL_ID;

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
        Dish dish = getCreated();
        service.create(dish, REST_CHILL_ID);
        assertMatch(service.getAll(REST_CHILL_ID, new Date()), dish, DISH04, DISH06, DISH05);
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void get() {
    }

    @Test
    public void getAll() {
    }
}