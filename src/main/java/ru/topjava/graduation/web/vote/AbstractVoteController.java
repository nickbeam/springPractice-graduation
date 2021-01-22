package ru.topjava.graduation.web.vote;

import org.hibernate.validator.internal.metadata.aggregated.ValidatableParametersMetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.topjava.graduation.model.Vote;
import ru.topjava.graduation.service.RestaurantService;
import ru.topjava.graduation.service.UserService;
import ru.topjava.graduation.service.VoteService;


import java.time.LocalDateTime;
import java.util.List;

import static ru.topjava.graduation.util.ValidationUtil.assureIdConsistent;
import static ru.topjava.graduation.util.ValidationUtil.checkNew;
import static ru.topjava.graduation.web.SecurityUtil.authUserId;

public abstract class AbstractVoteController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    public Vote create(int restaurantId) {
        log.info("create vote for restaurant with id = {}", restaurantId);
        int userId = authUserId();
        if (!service.isVotedToday(userId)) {
            Vote vote = new Vote(LocalDateTime.now(), userService.get(userId), restaurantService.get(restaurantId));
            return service.create(vote, userId, restaurantId);
        } else {
            update(restaurantId);
            return new Vote();
        }
    }

    public void update(int restaurantId) {
        int userId = authUserId();
        List<Vote> votes = service.getUserVoteToday(userId);
        Vote vote = votes.get(0);
        log.info("update vote {} for restaurant with id = {}", vote, restaurantId);
        assureIdConsistent(vote, vote.getId());
        service.update(vote, userId, restaurantId);
    }

    public void delete(int id, int restaurantId) {
        log.info("delete vote with id = {} for restaurant with id = {}", id, restaurantId);
        int userId = authUserId();
        service.delete(id, userId, restaurantId);
    }

    public Vote get(int id, int restaurantId) {
        log.info("get vote with id = {} for restaurant with id = {}", id, restaurantId);
        int userId = authUserId();
        return service.get(id, userId, restaurantId);
    }

    public int getCount(int restaurantId) {
        log.info("getCount votes for restaurant with id = {}", restaurantId);
        return service.getCount(restaurantId);
    }
}
