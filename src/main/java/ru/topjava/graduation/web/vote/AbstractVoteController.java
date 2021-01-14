package ru.topjava.graduation.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.topjava.graduation.model.Vote;
import ru.topjava.graduation.service.VoteService;


import static ru.topjava.graduation.util.ValidationUtil.assureIdConsistent;
import static ru.topjava.graduation.util.ValidationUtil.checkNew;

public abstract class AbstractVoteController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    public Vote create(Vote vote, int userId, int restaurantId) {
        log.info("create vote {} with user id = {} and restaurant id = {}", vote, userId, restaurantId);
        checkNew(vote);
        return service.create(vote, userId, restaurantId);
    }

    public void update(Vote vote, int id, int userId, int restaurantId) {
        log.info("update vote {} with user id = {} and restaurant id = {}", vote, userId, restaurantId);
        assureIdConsistent(vote, id);
        service.update(vote, userId, restaurantId);
    }

    public void delete(int id, int userId, int restaurantId) {
        log.info("delete vote with id = {}, user id = {} and restairant id = {}", id, userId, restaurantId);
        service.delete(id, userId, restaurantId);
    }

    public Vote get(int id, int userId, int restaurantId) {
        log.info("get vote with id = {}, user id = {} and restaurant id = {}", id, userId, restaurantId);
        return service.get(id, userId, restaurantId);
    }

    public int getCount(int restaurantId) {
        log.info("getCount votes from restaurant with id = {}", restaurantId);
        return service.getCount(restaurantId);
    }
}
