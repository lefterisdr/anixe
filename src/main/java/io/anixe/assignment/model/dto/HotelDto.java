package io.anixe.assignment.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.anixe.assignment.model.types.StarRatingType;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@JsonPropertyOrder({"id", "name", "address", "starRating", "bookings"})
public class HotelDto implements Serializable
{
    public HotelDto() {}

    public HotelDto(Integer id, String name, String address, StarRatingType starRating)
    {
        this.id = id;
        this.name = name;
        this.address = address;
        this.starRating = starRating;
    }

    @JsonProperty(value = "id")
    private Integer id;
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "address")
    private String address;
    @JsonProperty(value = "starRating")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private StarRatingType starRating;
    @JsonProperty(value = "bookings")
    @JsonManagedReference
    private Set<HotelBookingDto> bookings;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public StarRatingType getStarRating() {
        return starRating;
    }

    public void setStarRating(StarRatingType starRating) {
        this.starRating = starRating;
    }

    public Set<HotelBookingDto> getBookings() {
        return bookings;
    }

    public void setBookings(Set<HotelBookingDto> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "HotelDto{" +
                "id=" + id +
                ", name=" + name +
                ", address=" + address +
                ", starRating=" + String.valueOf(starRating) +
                '}';
    }

    @Override
    public boolean equals(Object hotel) {
        if (this == hotel)
        {
            return true;
        }

        if ((hotel == null) || (this.getClass() != hotel.getClass()))
        {
            return false;
        }

        HotelDto hotelDto = (HotelDto) hotel;

        return Objects.equals(id, hotelDto.getId())
                && Objects.equals(name, hotelDto.getName())
                && Objects.equals(address, hotelDto.getAddress())
                && Objects.equals(starRating, hotelDto.getStarRating());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, starRating);
    }
}
