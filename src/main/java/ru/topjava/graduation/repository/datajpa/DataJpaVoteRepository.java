package ru.topjava.graduation.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.topjava.graduation.model.Restaurant;
import ru.topjava.graduation.model.User;
import ru.topjava.graduation.model.Vote;
import ru.topjava.graduation.repository.VoteRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaVoteRepository implements VoteRepository {

    @Autowired
    private CrudVoteRepository crudRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Override
    public Vote save(Vote vote, int userId, int restaurantId) {
//        if (!vote.isNew() && (get(vote.getId(), ))) {
//
//        }
//        if (vote.getUser().getId() != userId || vote.getRestaurant().getId() != restaurantId) {
//            return null;
//        }
//        vote.setUser(crudUserRepository.getOne(userId));
//        vote.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
//        return crudRepository.save(vote);
        return null;
    }

    @Override
    public boolean delete(int id, int userId, int restaurantId) {
        return crudRepository.delete(id, userId, restaurantId) != 0;
    }

    @Override
    public Vote get(int id, int userId, int restaurantId) {
        return crudRepository.get(id, userId, restaurantId);
    }

    @Override
    public List<Vote> getByUser(int userId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return crudRepository.getByUser(userId, startDateTime, endDateTime);
    }

    @Override
    public int getCount(int restaurantId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return crudRepository.getCount(restaurantId, startDateTime, endDateTime);
    }
}
