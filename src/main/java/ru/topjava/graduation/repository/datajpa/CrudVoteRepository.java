package ru.topjava.graduation.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.graduation.model.Vote;

import java.time.LocalDateTime;
import java.util.List;


@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id = :id AND v.user.id = :userId AND v.restaurant.id = :restaurantId")
    int delete(@Param("id") int id, @Param("userId") int userId, @Param("restaurantId") int restaurantId);

    @Query("SELECT v FROM Vote v WHERE v.id = :id AND v.user.id = :userId AND v.restaurant.id = :restaurantId")
    Vote get(@Param("id") int id, @Param("userId") int userId, @Param("restaurantId") int restaurantId);

    @Query("SELECT v FROM Vote v WHERE v.user.id = :userId AND v.dateTime >= :startDateTime AND v.dateTime < :endDateTime ORDER BY v.dateTime")
    List<Vote> getByUser(@Param("userId") int userId,
                         @Param("startDateTime") LocalDateTime startDateTime,
                         @Param("endDateTime") LocalDateTime endDateTime);

    @Query("SELECT COUNT (v) FROM Vote v WHERE v.restaurant.id = :restaurantId AND v.dateTime >= :startDateTime AND v.dateTime < :endDateTime")
    int getCount(@Param("restaurantId") int restaurantId,
                        @Param("startDateTime") LocalDateTime startDateTime,
                        @Param("endDateTime") LocalDateTime endDateTime);
}
