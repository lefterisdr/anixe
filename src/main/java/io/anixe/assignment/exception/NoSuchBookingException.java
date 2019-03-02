package io.anixe.assignment.exception;

import java.text.MessageFormat;

public class NoSuchBookingException extends Throwable {
    public NoSuchBookingException(String... params)
    {
        super(MessageFormat.format("Booking {0} not found", params));
    }
}
