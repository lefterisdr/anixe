package io.anixe.assignment.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.anixe.assignment.model.types.CurrencyType;

@JsonPropertyOrder({"id", "hotelId", "customerName", "customerSurname", "numberOfPax", "priceAmount", "currency"})
public class HotelBookingDto
{
    @JsonProperty(value = "id")
    private Integer id;
    @JsonProperty(value = "hotelId")
    @JsonBackReference
    private HotelDto hotel;
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

    public HotelDto getHotel() {
        return hotel;
    }

    public void setHotel(HotelDto hotel) {
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
}
