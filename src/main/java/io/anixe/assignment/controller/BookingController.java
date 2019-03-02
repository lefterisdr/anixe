package io.anixe.assignment.controller;

import io.anixe.assignment.exception.NoSuchBookingException;
import io.anixe.assignment.exception.NoSuchHotelException;
import io.anixe.assignment.model.dto.BookingDto;
import io.anixe.assignment.model.dto.HotelBookingDto;
import io.anixe.assignment.service.BookingService;
import io.anixe.assignment.service.HotelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("api/v1/booking")
public class BookingController {
    private static final Logger logger = Logger.getLogger(BookingController.class);

    @Autowired
    BookingService bookingService;
    @Autowired
    HotelService hotelService;

    @GetMapping("/{id}")
    public BookingDto getBooking(@PathVariable Integer id) throws NoSuchBookingException {
        logger.info("Calling GET endpoint: api/v1/booking/" + id);
        return bookingService.getBooking(id);
    }

    @GetMapping
    public Set<HotelBookingDto> getBookingsByHotelId(@RequestParam Integer hotelId) throws NoSuchHotelException {
        logger.info("Calling GET endpoint: api/v1/booking?hotelId=" + hotelId);
        return hotelService.getBookingsByHotelId(hotelId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createBooking(@Valid @RequestBody BookingDto booking)
    {
        logger.info("Calling POST endpoint: api/v1/booking");
        logger.debug("Request body contains BookingDto: " + booking);

        return bookingService.createBooking(booking);
    }

    @PutMapping("/{id}")
    public void updateBooking(@PathVariable Integer id, @Valid @RequestBody BookingDto booking)
            throws NoSuchBookingException
    {
        logger.info("Calling PUT endpoint: api/v1/booking/" + id);
        logger.debug("Request body contains BookingDto: " + booking);

        bookingService.updateBooking(id, booking);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Integer id)
            throws NoSuchBookingException
    {
        logger.info("Calling DELETE endpoint: api/v1/booking/" + id);

        bookingService.deleteBooking(id);
    }
}
