package ru.topjava.graduation.web.vote;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.topjava.graduation.model.Vote;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController extends AbstractVoteController {

    static final String REST_URL = "/rest/votes";

    @Override
    public Vote create(Vote vote, int userId, int restaurantId) {
        return super.create(vote, userId, restaurantId);
    }

    @Override
    public void update(Vote vote, int id, int userId, int restaurantId) {
        super.update(vote, id, userId, restaurantId);
    }

    @Override
    public void delete(int id, int userId, int restaurantId) {
        super.delete(id, userId, restaurantId);
    }

    @Override
    public Vote get(int id, int userId, int restaurantId) {
        return super.get(id, userId, restaurantId);
    }

    @Override
    public int getCount(int restaurantId) {
        return super.getCount(restaurantId);
    }
}
