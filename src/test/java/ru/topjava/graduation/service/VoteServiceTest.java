package ru.topjava.graduation.service;

import org.junit.Assert;
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

import java.time.LocalDateTime;

import static ru.topjava.graduation.RestaurantTestData.*;
import static ru.topjava.graduation.UserTestData.*;
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
        service.create(VOTE01, ADMIN_ID, REST_CHILL_ID);
    }

    @Test
    public void update() {
        Vote vote = VoteTestData.getUpdated();
        service.update(VOTE01, vote.getUser().getId(), vote.getRestaurant().getId());
        assertMatch(vote, service.get(VOTE01_ID, USER_ID, REST_ELITE_ID));
    }

    @Test
    public void delete() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + VOTE01_ID);
        service.delete(VOTE01_ID, VOTE01.getUser().getId(), VOTE01.getRestaurant().getId());
        service.get(VOTE01_ID, VOTE01.getUser().getId(), VOTE01.getRestaurant().getId());
    }

    @Test
    public void get() {
        Vote newVote = VoteTestData.getCreated();
        Vote created = service.create(newVote, newVote.getUser().getId(), newVote.getRestaurant().getId());
        Vote vote = service.get(created.getId(), created.getUser().getId(), created.getRestaurant().getId());
        assertMatch(created, vote);
    }

    @Test
    public void isChangeable() {
        boolean changeableVote1 = service.isChangeable(VOTE01.getUser().getId());
        boolean changeableVote2 = service.isChangeable(VOTE02.getUser().getId());
        boolean changeableVote3 = service.isChangeable(VOTE03.getUser().getId());
        Assert.assertTrue(changeableVote1);
        Assert.assertFalse(changeableVote2);
        Assert.assertFalse(changeableVote3);
    }

    @Test
    public void getCount() {
        int countBrill = service.getCount(REST_BRILL_ID);
        int countChill = service.getCount(REST_CHILL_ID);
        int countElite = service.getCount(REST_ELITE_ID);
        Assert.assertEquals(countBrill, 2);
        Assert.assertEquals(countChill, 0);
        Assert.assertEquals(countElite, 1);
    }
}