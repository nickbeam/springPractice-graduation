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
    public Vote create(int restaurantId) {
        return super.create(restaurantId);
    }

    @Override
    public void update(int restaurantId) {
        super.update(restaurantId);
    }

    @Override
    public void delete(int id, int restaurantId) {
        super.delete(id, restaurantId);
    }

    @Override
    public Vote get(int id, int restaurantId) {
        return super.get(id, restaurantId);
    }

    @Override
    public int getCount(int restaurantId) {
        return super.getCount(restaurantId);
    }
}
