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
import ru.topjava.graduation.VoteTestData;
import ru.topjava.graduation.model.Vote;
import ru.topjava.graduation.util.exception.NotFoundException;

import static ru.topjava.graduation.RestaurantTestData.*;
import static ru.topjava.graduation.UserTestData.ADMIN_ID;
import static ru.topjava.graduation.UserTestData.USER_ID;
import static ru.topjava.graduation.VoteTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void create() {
        Vote newVote = VoteTestData.getCreated();
        service.delete(VOTE02_ID, ADMIN_ID, REST_BRILL_ID);
        Vote created = service.create(newVote, ADMIN_ID, REST_CHILL_ID);
        newVote.setId(created.getId());
        assertMatch(newVote, created);
    }

    @Test
    public void createWrongUserVote() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with present fields");
        service.create(VoteTestData.getCreated(), USER_ID, REST_CHILL_ID);
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
    public void isChangeable() {
    }

    @Test
    public void getCount() {
    }
}