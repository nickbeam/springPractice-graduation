package ru.topjava.graduation.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.graduation.model.Vote;
import ru.topjava.graduation.repository.VoteRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional(readOnly = true)
public class JpaVoteRepository implements VoteRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Vote save(Vote Vote) {
        return null;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Vote get(int id) {
        return null;
    }

    @Override
    public Vote getByUser(int userId) {
        return null;
    }

    @Override
    public int getCount(int restaurantId) {
        return 0;
    }
}
