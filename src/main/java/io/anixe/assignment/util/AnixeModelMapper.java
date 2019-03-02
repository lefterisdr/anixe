package io.anixe.assignment.util;

import io.anixe.assignment.model.dao.BookingDao;
import io.anixe.assignment.model.dao.HotelDao;
import io.anixe.assignment.model.dto.BookingDto;
import io.anixe.assignment.model.dto.BookingHotelDto;
import io.anixe.assignment.model.dto.HotelDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AnixeModelMapper {
    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void init() {
        modelMapper.createTypeMap(HotelDao.class, HotelDto.class)
                .addMappings(mapper -> mapper.map(HotelDao::getBookings, HotelDto::setBookings));
        modelMapper.createTypeMap(HotelDto.class, HotelDao.class)
                .addMappings(mapper -> mapper.map(HotelDto::getBookings, HotelDao::setBookings));
    }

    public HotelDto convertToDto(HotelDao hotelDao) {
        if (hotelDao == null) {
            return null;
        }

        HotelDto hotelDto = modelMapper.map(hotelDao, HotelDto.class);

        hotelDto.getBookings().stream().forEach(booking -> booking.setHotel(hotelDto));

        return hotelDto;
    }

    public HotelDao convertToDao(HotelDto hotelDto) {
        if (hotelDto == null) {
            return null;
        }

        HotelDao hotelDao = modelMapper.map(hotelDto, HotelDao.class);

        hotelDao.getBookings().stream().forEach(book -> book.setHotel(hotelDao));

        return hotelDao;
    }

    public HotelDao convertToDao(BookingHotelDto hotelDto) {
        if (hotelDto == null) {
            return null;
        }

        return modelMapper.map(hotelDto, HotelDao.class);
    }

    public HotelDto convertToDto(BookingHotelDto hotelDto) {
        if (hotelDto == null) {
            return null;
        }

        return modelMapper.map(hotelDto, HotelDto.class);
    }

    public BookingDto convertToDto(BookingDao bookingDao) {
        if (bookingDao == null) {
            return null;
        }

        return modelMapper.map(bookingDao, BookingDto.class);
    }

    public BookingDao convertToDao(BookingDto bookingDto) {
        if (bookingDto == null) {
            return null;
        }

        return modelMapper.map(bookingDto, BookingDao.class);
    }

    public Set<BookingDto> convertToDto(Set<BookingDao> bookings) {
        if (bookings == null) {
            return null;
        }

        return bookings.stream().map(this::convertToDto).collect(Collectors.toSet());
    }

    public Set<BookingDao> convertToDao(Set<BookingDto> bookings) {
        if (bookings == null) {
            return null;
        }

        return bookings.stream().map(this::convertToDao).collect(Collectors.toSet());
    }

    public List<HotelDto> convertToDto(List<HotelDao> hotels) {
        if (hotels == null) {
            return null;
        }

        return hotels.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<HotelDao> convertToDao(List<HotelDto> hotels) {
        if (hotels == null) {
            return null;
        }

        return hotels.stream().map(this::convertToDao).collect(Collectors.toList());
    }
}
