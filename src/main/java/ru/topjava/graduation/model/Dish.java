package ru.topjava.graduation.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id AND d.restaurant.id=:restaurantId"),
        @NamedQuery(name = Dish.GET, query = "SELECT d FROM Dish d WHERE d.id=:id AND d.restaurant.id=:restaurantId"),
        @NamedQuery(name = Dish.ALL_BY_RESTAURANT, query = "SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId AND d.date=:date ORDER BY d.name"),
})
@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "restaurant_id", "date"}, name = "dishes_unique_id_restaurant_date_idx")})
public class Dish extends AbstractNamedEntity {
    public static final String DELETE = "Dish.delete";
    public static final String GET = "Dish.get";
    public static final String ALL_BY_RESTAURANT = "Dish.getAllByRestToday";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(name = "date", nullable = false)
    @NotNull
    private Date date = new Date();

    @Column(name = "price", nullable = false)
    @NotNull
    private Integer price;

    public Dish() {
    }

    public Dish(Integer id, String name, Integer price) {
        this(id, name, new Date(), price);
    }

    public Dish(Integer id, String name, Date date, Integer price) {
        super(id, name);
        this.date = date;
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "date=" + date +
                ", price=" + price +
                '}';
    }
}
