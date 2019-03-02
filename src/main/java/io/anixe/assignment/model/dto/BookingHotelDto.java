package io.anixe.assignment.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.anixe.assignment.model.types.StarRatingType;

@JsonPropertyOrder({"id", "bookingId", "name", "address", "starRating"})
public class BookingHotelDto
{
    @JsonProperty(value = "id")
    private Integer id;
    @JsonProperty(value = "bookingId")
    @JsonBackReference
    private BookingDto booking;
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "address")
    private String address;
    @JsonProperty(value = "starRating")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private StarRatingType starRating;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BookingDto getBooking() {
        return booking;
    }

    public void setBooking(BookingDto booking) {
        this.booking = booking;
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
}
