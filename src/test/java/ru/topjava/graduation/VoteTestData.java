package ru.topjava.graduation;

import ru.topjava.graduation.model.Vote;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.topjava.graduation.RestaurantTestData.*;
import static ru.topjava.graduation.UserTestData.*;
import static ru.topjava.graduation.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {
    /*
       (100000, 100003, now() - interval '5 hour'),
       (100001, 100003, now() - interval '2 hour'),
       (100002, 100005, now());


    public Vote(Integer id, LocalDateTime dateTime, User user, Restaurant restaurant) {
    15-17
     */

    public static final int VOTE01_ID = START_SEQ + 15;
    public static final int VOTE02_ID = START_SEQ + 16;
    public static final int VOTE03_ID = START_SEQ + 17;

    public static final LocalDateTime CURRENT_DATE_TIME = LocalDateTime.now();

    public static final Vote VOTE01 = new Vote(VOTE01_ID, CURRENT_DATE_TIME, USER, REST_BRILL);
    public static final Vote VOTE02 = new Vote(VOTE02_ID, CURRENT_DATE_TIME, ADMIN, REST_BRILL);
    public static final Vote VOTE03 = new Vote(VOTE03_ID, CURRENT_DATE_TIME, GOURMET, REST_ELITE);

    public static Vote getCreated() {
        return new Vote(null, CURRENT_DATE_TIME, ADMIN, REST_CHILL);
    }

    public static Vote getUpdated() {
        return new Vote(VOTE01_ID, CURRENT_DATE_TIME, USER, REST_ELITE);
    }

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant", "user");
    }

    public static void assertMatch(Iterable<Vote> actual, Vote... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant", "user").isEqualTo(expected);
    }
}
