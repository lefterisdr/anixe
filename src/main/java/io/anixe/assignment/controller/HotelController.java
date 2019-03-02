package io.anixe.assignment.controller;

import io.anixe.assignment.exception.NoSuchBookingException;
import io.anixe.assignment.exception.NoSuchHotelException;
import io.anixe.assignment.model.dto.HotelDto;
import io.anixe.assignment.service.BookingService;
import io.anixe.assignment.service.HotelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/hotel")
public class HotelController {
    private static final Logger logger = Logger.getLogger(BookingController.class);

    @Autowired
    HotelService hotelService;
    @Autowired
    BookingService bookingService;

    @GetMapping
    public List<HotelDto> getAllHotels()
    {
        return hotelService.getAllHotels();
    }

    @GetMapping("/{id}")
    public HotelDto getHotel(@PathVariable Integer id) throws NoSuchHotelException {
        logger.info("Calling GET endpoint: api/v1/hotel/" + id);

        return hotelService.getHotel(id);
    }

    @GetMapping("/{hotelId}/priceAmount")
    public Float getTotalPriceAmount(@PathVariable Integer hotelId) throws NoSuchHotelException {
        logger.info("Calling GET endpoint: api/v1/hotel/" + hotelId + "/priceAmount");

        return hotelService.getTotalPriceAmount(hotelId);
    }

    @GetMapping("/surname/{surname}")
    public Set<HotelDto> getHotelsBySurname(@PathVariable String surname) throws NoSuchBookingException {
        logger.info("Calling GET endpoint: api/v1/hotel/surname/" + surname);

        return bookingService.getHotelsBySurname(surname);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createHotel(@RequestBody HotelDto hotel)
    {
        logger.info("Calling POST endpoint: api/v1/hotel");
        logger.debug("Request body contains HotelDto: " + hotel);

        return hotelService.createHotel(hotel);
    }

    @PutMapping("/{id}")
    public void updateHotel(@PathVariable Integer id, @RequestBody HotelDto hotel)
            throws NoSuchHotelException
    {
        logger.info("Calling PUT endpoint: api/v1/hotel/" + id);
        logger.debug("Request body contains HotelDto: " + hotel);

        hotelService.updateHotel(id, hotel);
    }

    @DeleteMapping("/{id}")
    public void deleteHotel(@PathVariable Integer id)
            throws NoSuchHotelException
    {
        logger.info("Calling DELETE endpoint: api/v1/hotel/" + id);

        hotelService.deleteHotel(id);
    }
}
