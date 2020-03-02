package ru.topjava.graduation.to;

import ru.topjava.graduation.model.Dish;
import ru.topjava.graduation.model.Restaurant;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

public class Menu {
    private Date date = new Date();
    private Restaurant restaurant;
    private Set<Dish> dishes;

    public Menu() {
    }

    public Menu(Date date, Restaurant restaurant, Set<Dish> dishes) {
        this.date = date;
        this.restaurant = restaurant;
        setDishes(dishes);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes.isEmpty() ? Collections.emptySet() : Set.copyOf(dishes);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "date=" + date +
                ", restaurant=" + restaurant +
                ", dishes=" + dishes +
                '}';
    }
}
