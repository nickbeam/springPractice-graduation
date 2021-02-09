package ru.topjava.graduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.topjava.graduation.model.Vote;
import ru.topjava.graduation.repository.VoteRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static ru.topjava.graduation.util.ValidationUtil.*;

@Service
public class VoteService {
    private VoteRepository repository;

    @Autowired
    public VoteService(VoteRepository repository) {
        this.repository = repository;
    }

    public Vote create(Vote vote, int userId, int restaurantId) {
        Assert.notNull(vote, "vote must not be null");
        return checkNotFound(isChangeable(userId) ? repository.save(vote, userId, restaurantId) : null, "present fields");
    }

    public void update(Vote vote, int userId, int restaurantId) {
        Assert.notNull(vote, "vote must not be null");
        checkNotFoundWithId(repository.save(vote, userId, restaurantId), vote.getId());
    }

    public void delete(int id, int userId, int restaurantId) {
        checkNotFoundWithId(repository.delete(id, userId, restaurantId), id);
    }

    public Vote get(int id, int userId, int restaurantId) {
        return checkNotFoundWithId(repository.get(id, userId, restaurantId), id);
    }

    public List<Vote> getUserVoteToday(int userId) {
        return checkVoteIsSingleToday(repository.getByUser(userId,
                LocalDateTime.of(LocalDate.now(), LocalTime.MIN),
                LocalDateTime.of(LocalDate.now(), LocalTime.MAX)));
    }

    public boolean isVotedToday(int userId) {
        return repository.getByUser(userId,
                LocalDateTime.of(LocalDate.now(), LocalTime.MIN),
                LocalDateTime.of(LocalDate.now(), LocalTime.MAX))
                .size() > 0;
    }

    public boolean isChangeable(int userId) {
        return repository.getByUser(userId,
                LocalDateTime.of(LocalDate.now(), Vote.TIME_CHANGE_MIND),
                LocalDateTime.of(LocalDate.now(), LocalTime.MAX))
                .isEmpty();
    }

    public int getCount(int restaurantId) {
        return repository.getCount(restaurantId,
                LocalDateTime.of(LocalDate.now(), LocalTime.MIN),
                LocalDateTime.of(LocalDate.now(), LocalTime.MAX));
    }
}
