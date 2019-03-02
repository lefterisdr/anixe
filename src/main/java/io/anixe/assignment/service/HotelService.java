package io.anixe.assignment.service;

import io.anixe.assignment.exception.NoSuchHotelException;
import io.anixe.assignment.model.dao.HotelDao;
import io.anixe.assignment.model.dto.HotelBookingDto;
import io.anixe.assignment.model.dto.HotelDto;
import io.anixe.assignment.repository.HotelRepo;
import io.anixe.assignment.util.AnixeModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;

@Service
public class HotelService {
    @Autowired
    private HotelRepo hotelRepo;
    @Autowired
    private AnixeModelMapper modelMapper;

    public List<HotelDto> getAllHotels()
    {
        return modelMapper.convertToDto(hotelRepo.findAll());
    }

    public HotelDto getHotel(Integer id) throws NoSuchHotelException {
        if (id == null)
        {
            throw new NoSuchHotelException();
        }

        return this.findHotel(id);
    }

    public Set<HotelBookingDto> getBookingsByHotelId(Integer hotelId) throws NoSuchHotelException {
        return this.findHotel(hotelId).getBookings();
    }

    public HotelDto getHotelByName(String name) throws NoSuchHotelException {
        return modelMapper.convertToDto(hotelRepo.findByName(name).orElseThrow(() -> new NoSuchHotelException(name + "")));
    }

    public Float getTotalPriceAmount(Integer hotelId) throws NoSuchHotelException {
        return this.findHotel(hotelId).getBookings().stream().map(HotelBookingDto::getPriceAmount).reduce(Float::sum).orElse(0f);
    }

    public Integer createHotel(HotelDto hotel)
    {
        return hotelRepo.saveAndFlush(modelMapper.convertToDao(hotel)).getId();
    }

    public Integer createHotel(HotelDao hotel)
    {
        return hotelRepo.saveAndFlush(hotel).getId();
    }

    public HotelDao updateHotel(Integer id, HotelDto hotel)
            throws NoSuchHotelException
    {
        HotelDto existingHotel = this.findHotel(id);
        BeanUtils.copyProperties(hotel, existingHotel);
        return hotelRepo.saveAndFlush(modelMapper.convertToDao(existingHotel));
    }

    public void deleteHotel(Integer id)
            throws NoSuchHotelException
    {
        HotelDto existingHotel = this.findHotel(id);
        hotelRepo.delete(modelMapper.convertToDao(existingHotel));
    }

    private HotelDto findHotel(Integer id) throws NoSuchHotelException {
        return modelMapper.convertToDto(hotelRepo.findById(id).orElseThrow(() -> new NoSuchHotelException(id + "")));
    }
}
