package ru.topjava.graduation.repository;

import ru.topjava.graduation.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository {
    Vote save(Vote vote, int userId, int restaurantId);

    boolean delete(int id, int userId, int restaurantId);

    Vote get(int id, int userId, int restaurantId);

    List<Vote> getByUser(int userId, LocalDateTime startDateTime, LocalDateTime endDateTime);

    int getCount(int restaurantId, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
