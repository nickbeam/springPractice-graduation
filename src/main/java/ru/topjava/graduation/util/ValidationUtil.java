package ru.topjava.graduation.util;


import ru.topjava.graduation.model.AbstractBaseEntity;
import ru.topjava.graduation.model.Vote;
import ru.topjava.graduation.util.exception.NotFoundException;
import ru.topjava.graduation.util.exception.WrongVoteException;

import java.util.List;

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static List<Vote> checkVoteIsSingleToday(List<Vote> list) {
        if (list.size() > 1) {
            throw new WrongVoteException("Duplicate user votes per date");
        }
        return list;
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static void checkNew(AbstractBaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(AbstractBaseEntity entity, int id) {
//      conservative when you reply, but accept liberally (http://stackoverflow.com/a/32728226/548473)
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.getId() != id) {
            throw new IllegalArgumentException(entity + " must be with id=" + id);
        }
    }
}