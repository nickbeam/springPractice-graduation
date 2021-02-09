package ru.topjava.graduation.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.topjava.graduation.model.Vote;
import ru.topjava.graduation.service.RestaurantService;
import ru.topjava.graduation.service.UserService;
import ru.topjava.graduation.service.VoteService;


import java.time.LocalDateTime;
import java.util.List;

import static ru.topjava.graduation.web.SecurityUtil.authUserId;

public abstract class AbstractVoteController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    public void vote(int restaurantId) {
        int userId = authUserId();
        log.info("user with id = {} vote for restaurant with id = {}", userId, restaurantId);
        List<Vote> votes = service.getUserVoteToday(userId);
        if (votes.isEmpty()) {
            Vote vote = new Vote(LocalDateTime.now(), userService.get(userId), restaurantService.get(restaurantId));
            service.create(vote, userId, restaurantId);
        } else {
            service.update(votes.stream().findFirst().orElse(null), userId, restaurantId);
        }
    }

    public Vote getUserVoteToday() {
        int userId = authUserId();
        log.info("get user with id = {} vote today", userId);
        List<Vote> votes = service.getUserVoteToday(userId);
        return votes.stream().findFirst().orElse(null);
    }

    public int getCount(int restaurantId) {
        log.info("getCount votes for restaurant with id = {}", restaurantId);
        return service.getCount(restaurantId);
    }
}
