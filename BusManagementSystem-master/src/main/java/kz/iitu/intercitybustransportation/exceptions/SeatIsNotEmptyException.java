package kz.iitu.intercitybustransportation.exceptions;


public class SeatIsNotEmptyException extends RuntimeException {

    public SeatIsNotEmptyException(String message) {
        super(message);
    }
}

