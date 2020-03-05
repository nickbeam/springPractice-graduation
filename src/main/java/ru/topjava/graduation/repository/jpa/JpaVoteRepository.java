package ru.topjava.graduation.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.graduation.model.Restaurant;
import ru.topjava.graduation.model.User;
import ru.topjava.graduation.model.Vote;
import ru.topjava.graduation.repository.VoteRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaVoteRepository implements VoteRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Vote save(Vote vote, int userId, int restaurantId) {
        if (vote.getUser() == null) {
            vote.setUser(em.getReference(User.class, userId));
        } else if (vote.getRestaurant() == null) {
             vote.setRestaurant(em.getReference(Restaurant.class, restaurantId));
        }
        if (vote.isNew()) {
            em.persist(vote);
            return vote;
        } else {
            return get(vote.getId(), userId, restaurantId) == null ? null : em.merge(vote);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId, int restaurantId) {
        return em.createNamedQuery(Vote.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .setParameter("restaurantId", restaurantId)
                .executeUpdate() != 0;
    }

    @Override
    public Vote get(int id, int userId, int restaurantId) {
        return DataAccessUtils.singleResult(em.createNamedQuery(Vote.GET, Vote.class)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .setParameter("restaurantId", restaurantId)
                .getResultList());
    }

    @Override
    public Vote getByUser(int userId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        List<Vote> votes = em.createNamedQuery(Vote.GET_BY_USER, Vote.class)
                .setParameter("userId", userId)
                .setParameter("startDateTime", startDateTime)
                .setParameter("endDateTime", endDateTime)
                .getResultList();
        return DataAccessUtils.singleResult(votes);
    }

    @Override
    public int getCount(int restaurantId) {
        return em.createNamedQuery(Vote.GET_COUNT).setParameter("restaurantId", restaurantId).executeUpdate();
    }
}