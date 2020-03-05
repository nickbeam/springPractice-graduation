package ru.topjava.graduation.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NamedQueries({
        @NamedQuery(name = Vote.DELETE, query = "DELETE FROM Vote v WHERE v.id=:id AND v.user.id=:userId AND v.restaurant.id=:restaurantId"),
        @NamedQuery(name = Vote.GET, query = "SELECT v FROM Vote v WHERE v.id=:id AND v.user.id=:userId AND v.restaurant.id=:restaurantId ORDER BY v.dateTime"),
        @NamedQuery(name = Vote.GET_BY_USER, query = "SELECT v FROM Vote v WHERE v.user.id=:userId AND v.dateTime >=: startDateTime AND v.dateTime <=: endDateTime ORDER BY v.dateTime"),
        @NamedQuery(name = Vote.GET_COUNT, query = "SELECT COUNT (v) FROM Vote v WHERE v.restaurant=:restaurant AND v.dateTime >=: startDateTime AND v.dateTime <=: endDateTime ORDER BY v.dateTime"),
})
@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_time"}, name = "votes_unique_user_datetime_idx")})
public class Vote extends AbstractBaseEntity {
    public static final String DELETE = "Vote.delete";
    public static final String GET = "Vote.get";
    public static final String GET_BY_USER = "Vote.getByUser";
    public static final String GET_COUNT = "Vote.getCount";
    public static final LocalTime TIME_CHANGE_MIND = LocalTime.of(11, 0);

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(Integer id, LocalDateTime dateTime, User user, Restaurant restaurant) {
        super(id);
        this.dateTime = dateTime;
        this.user = user;
        this.restaurant = restaurant;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "dateTime=" + dateTime +
                ", user=" + user +
                ", restaurant=" + restaurant +
                '}';
    }
}
