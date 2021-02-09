package ru.topjava.graduation.web.vote;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.topjava.graduation.model.Vote;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController extends AbstractVoteController {

    static final String REST_URL = "/rest/votes";

    @Override
    @PostMapping("/{restaurantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void vote(@PathVariable int restaurantId) {
        super.vote(restaurantId);
    }

    @Override
    @GetMapping
    public Vote getUserVoteToday() {
        return super.getUserVoteToday();
    }

    @Override
    @GetMapping("/{restaurantId}")
    public int getCount(@PathVariable int restaurantId) {
        return super.getCount(restaurantId);
    }
}
