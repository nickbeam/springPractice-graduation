package ru.topjava.graduation;

import ru.topjava.graduation.model.Restaurant;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.topjava.graduation.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final int REST_BRILL_ID = START_SEQ + 3;
    public static final int REST_CHILL_ID = START_SEQ + 4;
    public static final int REST_ELITE_ID = START_SEQ + 5;
    public static final int REST_WRONG_ID = 999999;

    public static final Restaurant REST_BRILL =
            new Restaurant(REST_BRILL_ID, "Бриллиантовая рука", "Невский д.55", "+7(812)3223232", "info@br.ru");
    public static final Restaurant REST_CHILL =
            new Restaurant(REST_CHILL_ID, "Чиллаут снек бар", "Большой Сампсониевский д.4", "+7(812)5558877", "info@chill.ru");
    public static final Restaurant REST_ELITE =
            new Restaurant(REST_ELITE_ID, "Элит", "Набережная реки Мойки д.20", "+7(812)4448899", "info@elite.ru");

    public static final List<Restaurant> RESTAURANTS = Arrays.asList(REST_BRILL, REST_CHILL, REST_ELITE);

    public static Restaurant getCreated() {
        return new Restaurant(null, "newRestaurant", "newAddress", "newPhone", "new@rest.com");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(REST_BRILL_ID, "Бриллиантовая рука", "newAwesomeAddress", "777-777", "info@br.ru");
    }

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields().isEqualTo(expected);
    }
}
