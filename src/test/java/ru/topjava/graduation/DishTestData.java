package ru.topjava.graduation;

import ru.topjava.graduation.model.Dish;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static ru.topjava.graduation.model.AbstractBaseEntity.START_SEQ;

public class DishTestData {

    public static final int DISH01_ID = START_SEQ + 7;
    public static final int DISH02_ID = START_SEQ + 8;
    public static final int DISH03_ID = START_SEQ + 9;

    public static final int DISH04_ID = START_SEQ + 10;
    public static final int DISH05_ID = START_SEQ + 11;
    public static final int DISH06_ID = START_SEQ + 12;

    public static final int DISH07_ID = START_SEQ + 13;
    public static final int DISH08_ID = START_SEQ + 14;
    public static final int DISH09_ID = START_SEQ + 15;

    public static final int DISH_WRONG_ID = 999999;

    public static final Date CURRENT_DATE = new Date(System.currentTimeMillis());

    public static final Dish DISH01 =
            new Dish(DISH01_ID, "Стейк", CURRENT_DATE, 1000);
    public static final Dish DISH02 =
            new Dish(DISH02_ID, "Мясо по французски", CURRENT_DATE, 750);
    public static final Dish DISH03 =
            new Dish(DISH03_ID, "Сибас", CURRENT_DATE, 590);

    public static final Dish DISH04 =
            new Dish(DISH04_ID, "Гренки", CURRENT_DATE, 150);
    public static final Dish DISH05 =
            new Dish(DISH05_ID, "Чипсы", CURRENT_DATE, 90);
    public static final Dish DISH06 =
            new Dish(DISH06_ID, "Нагитсы", CURRENT_DATE, 210);

    public static final Dish DISH07 =
            new Dish(DISH07_ID, "Оленина", CURRENT_DATE, 900);
    public static final Dish DISH08 =
            new Dish(DISH08_ID, "Карп", CURRENT_DATE, 1200);
    public static final Dish DISH09 =
            new Dish(DISH09_ID, "Лобстер", CURRENT_DATE, 1500);

    public static final List<Dish> DISHES = Arrays.asList(DISH01, DISH02, DISH03, DISH04, DISH05, DISH06, DISH07, DISH08, DISH09);

    public static Dish getCreated() {
        return new Dish(null, "newDish", CURRENT_DATE, 555);
    }

    public static Dish getUpdated() {
        return new Dish(DISH01_ID, "updatedDishName", CURRENT_DATE, 2500);
    }

    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant", "date");
    }

    public static void assertMatch(Iterable<Dish> actual, Dish... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant", "date").isEqualTo(expected);
    }
}
