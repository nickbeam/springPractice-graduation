package ru.topjava.graduation.repository;

import ru.topjava.graduation.model.Vote;

public interface VoteRepository {
    Vote save(Vote Vote);

    boolean delete(int id);

    Vote get(int id);

    Vote getByUser(int userId);

    int getCount(int restaurantId);
}
