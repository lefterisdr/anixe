package io.anixe.assignment.exception;

import java.text.MessageFormat;

public class NoSuchHotelException extends Throwable {
    public NoSuchHotelException(String... params)
    {
        super(MessageFormat.format("Hotel {0} not found", params));
    }
}
