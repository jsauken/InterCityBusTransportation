package kz.iitu.intercitybustransportation.exceptions;

public class DuplicateException extends RuntimeException {
    private static final long serialVerisionUID = 6;
    public DuplicateException(String message) {
        super(message);
    }
}