package io.anixe.assignment.service;

import io.anixe.assignment.exception.NoSuchBookingException;
import io.anixe.assignment.model.dao.HotelDao;
import io.anixe.assignment.model.dto.BookingDto;
import io.anixe.assignment.model.dto.BookingHotelDto;
import io.anixe.assignment.model.dto.HotelDto;
import io.anixe.assignment.repository.BookingRepo;
import io.anixe.assignment.repository.HotelRepo;
import io.anixe.assignment.util.AnixeModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BookingService {
    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private HotelRepo hotelRepo;
    @Autowired
    private AnixeModelMapper modelMapper;

    public BookingDto getBooking(Integer id) throws NoSuchBookingException {
        return this.findBooking(id);
    }

    public Set<BookingDto> getBookingsBySurname(String surname) {
        return modelMapper.convertToDto(bookingRepo.findByCustomerSurname(surname));
    }

    public Set<HotelDto> getHotelsBySurname(String surname) {
        Set<HotelDto> hotels = new HashSet<>();

        modelMapper.convertToDto(bookingRepo.findByCustomerSurname(surname)).stream()
                .forEach(booking -> hotels.add(modelMapper.convertToDto(booking.getHotel())));

        return hotels;
    }

    public Integer createBooking(BookingDto booking)
    {
        BookingHotelDto hotel = booking.getHotel();

        HotelDao hotelDao = hotelRepo.findByName(hotel.getName()).orElse(hotelRepo.saveAndFlush(modelMapper.convertToDao(hotel)));

        hotel.setId(hotelDao.getId());

        return bookingRepo.saveAndFlush(modelMapper.convertToDao(booking)).getId();
    }

    public void updateBooking(Integer id, BookingDto booking)
            throws NoSuchBookingException
    {
        BookingDto existingBooking = this.findBooking(id);
        BookingHotelDto hotel = booking.getHotel();

        HotelDao hotelDao = hotelRepo.findByName(hotel.getName()).orElse(hotelRepo.saveAndFlush(modelMapper.convertToDao(hotel)));

        hotel.setId(hotelDao.getId());

        BeanUtils.copyProperties(booking, existingBooking);
        bookingRepo.saveAndFlush(modelMapper.convertToDao(existingBooking));
    }

    public void deleteBooking(Integer id)
            throws NoSuchBookingException
    {
        BookingDto existingBooking = this.findBooking(id);
        bookingRepo.delete(modelMapper.convertToDao(existingBooking));
    }

    private BookingDto findBooking(Integer id) throws NoSuchBookingException {
        return modelMapper.convertToDto(bookingRepo.findById(id).orElseThrow(() -> new NoSuchBookingException(id + "")));
    }
}
