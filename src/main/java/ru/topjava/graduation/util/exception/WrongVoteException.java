package ru.topjava.graduation.util.exception;

public class WrongVoteException extends RuntimeException {
    public WrongVoteException(String message) {
        super(message);
    }
}
