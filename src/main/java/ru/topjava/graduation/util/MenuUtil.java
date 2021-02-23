package ru.topjava.graduation.util;

import ru.topjava.graduation.model.Dish;
import ru.topjava.graduation.to.Menu;

import java.util.Collection;
import java.util.Date;

public class MenuUtil {

    private MenuUtil() {

    }

    public static Menu getMenu(Collection<Dish> dishes, Date date) {

        return new Menu();
    }

}
