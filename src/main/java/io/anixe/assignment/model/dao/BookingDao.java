package io.anixe.assignment.model.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.anixe.assignment.model.types.CurrencyType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "booking")
public class BookingDao
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "booking_id")
    private Integer id;
    @ManyToOne
    @NotNull
    @JoinColumn(name="hotel_id")
    @JsonBackReference
    private HotelDao hotel;
    @Column(name = "customer_name", length = 30)
    private String customerName;
    @Column(name = "customer_surname", length = 50)
    private String customerSurname;
    @Column(name = "number_of_pax")
    private Integer numberOfPax;
    @Column(name = "price_amount")
    private Float priceAmount;
    @Column(name = "currency", length=5)
    @Enumerated(EnumType.STRING)
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

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public HotelDao getHotel() {
        return hotel;
    }

    public void setHotel(HotelDao hotel) {
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
