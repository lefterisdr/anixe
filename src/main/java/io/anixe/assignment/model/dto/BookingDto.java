package io.anixe.assignment.model.dto;

import com.fasterxml.jackson.annotation.*;
import io.anixe.assignment.model.types.CurrencyType;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@JsonPropertyOrder({"id", "hotel", "customerName", "customerSurname", "numberOfPax", "priceAmount", "currency"})
public class BookingDto
{
    @JsonProperty(value = "id")
    private Integer id;
    @JsonProperty(value = "hotel")
    @JsonManagedReference
    @NotNull
    private BookingHotelDto hotel;
    @JsonProperty(value = "customerName")
    private String customerName;
    @JsonProperty(value = "customerSurname")
    private String customerSurname;
    @JsonProperty(value = "numberOfPax")
    private Integer numberOfPax;
    @JsonProperty(value = "priceAmount")
    private Float priceAmount;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @JsonProperty(value = "currency")
    private CurrencyType currency;

    public Float getPriceAmount() {
        return priceAmount;
    }

    public void setPriceAmount(Float priceAmount) {
        this.priceAmount = priceAmount;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BookingHotelDto getHotel() {
        return hotel;
    }

    public void setHotel(BookingHotelDto hotel) {
        this.hotel = hotel;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public Integer getNumberOfPax() {
        return numberOfPax;
    }

    public void setNumberOfPax(Integer numberOfPax) {
        this.numberOfPax = numberOfPax;
    }

    @Override
    public String toString() {
        return "BookingDto{" +
                "id=" + id +
                ", customerName=" + customerName +
                ", customerSurname=" + customerSurname +
                ", numberOfPax=" + numberOfPax +
                ", priceAmount=" + priceAmount +
                ", currency=" + String.valueOf(currency) +
                '}';
    }

    @Override
    public boolean equals(Object booking) {
        if (this == booking)
        {
            return true;
        }

        if ((booking == null) || (this.getClass() != booking.getClass()))
        {
            return false;
        }

        BookingDto bookingDto = (BookingDto) booking;

        return Objects.equals(id, bookingDto.getId())
                && Objects.equals(customerName, bookingDto.getCustomerName())
                && Objects.equals(customerSurname, bookingDto.getCustomerSurname())
                && Objects.equals(numberOfPax, bookingDto.getNumberOfPax())
                && Objects.equals(priceAmount, bookingDto.getPriceAmount())
                && Objects.equals(currency, bookingDto.getCurrency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerName, customerSurname, numberOfPax, priceAmount, currency);
    }
}
