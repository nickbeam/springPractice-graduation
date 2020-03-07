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
import ru.topjava.graduation.RestaurantTestData;
import ru.topjava.graduation.model.Restaurant;
import ru.topjava.graduation.util.exception.NotFoundException;

import static ru.topjava.graduation.RestaurantTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void create() {
        Restaurant newRestaurant = getCreated();
        service.create(newRestaurant);
        assertMatch(service.getAll(), newRestaurant, REST_BRILL, REST_CHILL, REST_ELITE);
    }

    @Test
    public void update() {
        Restaurant restaurant = getUpdated();
        service.update(restaurant);
        assertMatch(service.get(REST_BRILL_ID), restaurant);
        assertMatch(service.getAll(), restaurant, REST_CHILL, REST_ELITE);
    }

    @Test
    public void updateNotFound() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + REST_WRONG_ID);
        Restaurant restaurant = service.get(REST_CHILL_ID);
        restaurant.setId(REST_WRONG_ID);
        service.update(restaurant);
    }

    @Test
    public void delete() {
        service.delete(REST_BRILL_ID);
        assertMatch(service.getAll(), REST_CHILL, REST_ELITE);
    }

    @Test
    public void deleteNotFound() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + REST_WRONG_ID);
        service.delete(REST_WRONG_ID);
    }

    @Test
    public void get() {
        assertMatch(service.get(REST_CHILL_ID), REST_CHILL);
    }

    @Test
    public void getNotFound() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + REST_WRONG_ID);
        service.get(REST_WRONG_ID);
    }

    @Test
    public void getAll() {
        assertMatch(service.getAll(), RESTAURANTS);
    }
}