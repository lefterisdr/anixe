package io.anixe.assignment.model.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.anixe.assignment.model.types.StarRatingType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "hotel")
public class HotelDao
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hotel_id")
    private Integer id;
    @Column(name = "name", length = 30)
    private String name;
    @Column(name = "address", length = 100)
    private String address;
    @Column(name = "star_rating", length=20)
    @Enumerated(EnumType.STRING)
    private StarRatingType starRating;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="hotel_id")
    @JsonManagedReference
    private Set<BookingDao> bookings;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public StarRatingType getStarRating() {
        return starRating;
    }

    public void setStarRating(StarRatingType starRating) {
        this.starRating = starRating;
    }

    public Set<BookingDao> getBookings() {
        return bookings;
    }

    public void setBookings(Set<BookingDao> bookings) {
        this.bookings = bookings;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
