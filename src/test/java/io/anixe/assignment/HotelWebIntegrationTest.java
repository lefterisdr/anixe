package io.anixe.assignment;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import io.anixe.assignment.model.dto.BookingDto;
import io.anixe.assignment.model.dto.BookingHotelDto;
import io.anixe.assignment.model.dto.HotelBookingDto;
import io.anixe.assignment.model.dto.HotelDto;
import io.anixe.assignment.model.types.CurrencyType;
import io.anixe.assignment.model.types.StarRatingType;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AnixeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"test"})
public class HotelWebIntegrationTest
{
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    ObjectMapper objectMapper = new ObjectMapper();
    HttpEntity<String> entityNoBody = new HttpEntity<>(null, new HttpHeaders());
    ResponseEntity<String> response;

    @Test
    public void testGetAllHotels()
            throws JSONException, IOException
    {
        JsonNode jsonResponse = this.getAllHotels();

        ObjectReader reader = objectMapper.readerFor(new TypeReference<List<HotelDto>>() {});

        List<HotelDto> hotels = reader.readValue(jsonResponse);

        List<String> actualHotelNames = hotels.stream().map(HotelDto::getName).collect(Collectors.toList());
        List<String> expectedHotelNames = Stream.of("Grande Bretagne", "King George", "President", "Hilton").collect(Collectors.toList());

        assertThat(expectedHotelNames, everyItem(isIn(actualHotelNames)));
    }

    @Test
    public void testCreateHotel()
            throws JSONException, IOException
    {
        Set<HotelBookingDto> bookings = new HashSet<>();
        HotelBookingDto booking = new HotelBookingDto();
        booking.setCurrency(CurrencyType.AUD);
        booking.setCustomerName("Is");
        booking.setCustomerSurname("Nogood");
        booking.setNumberOfPax(3);
        booking.setPriceAmount(766f);
        bookings.add(booking);

        HotelDto hotel = new HotelDto();
        hotel.setAddress("Arizona");
        hotel.setName("The Famous One");
        hotel.setStarRating(StarRatingType.FIVE_STAR);
        hotel.setBookings(bookings);

        int count = this.getAllHotels().size();
        this.createHotel(hotel);
        assertThat(this.getAllHotels().size(), is(count+1));

        assertThat(this.getHotelBookings(5).size(), is(1));

        assertThat(this.getHotelsBySurname("Nogood").size(), is(1));

        assertThat(this.getTotalPriceAmount(5), is(766f));
    }

    @Test
    public void testCreateBooking()
            throws JSONException, IOException
    {
        BookingHotelDto hotel = new BookingHotelDto();
        hotel.setAddress("Mordor");
        hotel.setName("Grand Canyon");
        hotel.setStarRating(StarRatingType.TWO_STAR);

        BookingDto booking = new BookingDto();
        booking.setCurrency(CurrencyType.AUD);
        booking.setCustomerName("Jon");
        booking.setCustomerSurname("Snow");
        booking.setNumberOfPax(5);
        booking.setPriceAmount(2701f);
        booking.setHotel(hotel);

        int count = this.getAllHotels().size();
        this.createBooking(booking);
        assertThat(this.getAllHotels().size(), is(count+1));

        assertThat(this.getHotelsBySurname("Snow").size(), is(2));
    }

    @Test
    public void testHotelBookings()
            throws JSONException, IOException
    {
        assertThat(this.getHotelBookings(1).size(), is(2));
        assertThat(this.getHotelBookings(2).size(), is(3));
        assertThat(this.getHotelBookings(3).size(), is(1));
        assertThat(this.getHotelBookings(4).size(), is(0));
    }

    @Test
    public void testHotelsBySurname()
            throws JSONException, IOException
    {
        assertThat(this.getHotelsBySurname("Hanks").size(), is(2));
        assertThat(this.getHotelsBySurname("Danvers").size(), is(1));
        assertThat(this.getHotelsBySurname("Dritsas").size(), is(0));
    }

    @Test
    public void testTotalPriceAmount()
            throws JSONException, IOException
    {
        assertThat(this.getTotalPriceAmount(1), is(1328f));
        assertThat(this.getTotalPriceAmount(2), is(2118f));
        assertThat(this.getTotalPriceAmount(3), is(480f));
        assertThat(this.getTotalPriceAmount(4), is(0f));
    }

    private JsonNode getAllHotels() throws JSONException, IOException
    {
        response = restTemplate.exchange(
                this.createURLWithPort("/api/v1/hotel"),
                HttpMethod.GET, entityNoBody, String.class);

        this.assertStatus(HttpStatus.OK);

        return objectMapper.readTree(response.getBody());
    }

    private JsonNode getHotelBookings(Integer _hotelId)
            throws JSONException, IOException
    {
        response = restTemplate.exchange(
                this.createURLWithPort("/api/v1/booking?hotelId="+_hotelId),
                HttpMethod.GET, entityNoBody, String.class);

        this.assertStatus(HttpStatus.OK);

        return objectMapper.readTree(response.getBody());
    }

    private Float getTotalPriceAmount(Integer _hotelId)
            throws JSONException, IOException
    {
        response = restTemplate.exchange(
                this.createURLWithPort("/api/v1/hotel/" + _hotelId + "/priceAmount"),
                HttpMethod.GET, entityNoBody, String.class);

        this.assertStatus(HttpStatus.OK);

        return objectMapper.treeToValue(objectMapper.readTree(response.getBody()), Float.class);
    }

    private JsonNode getHotelsBySurname(String _surname)
            throws JSONException, IOException
    {
        response = restTemplate.exchange(
                this.createURLWithPort("/api/v1/hotel/surname/"+_surname),
                HttpMethod.GET, entityNoBody, String.class);

        this.assertStatus(HttpStatus.OK);

        return objectMapper.readTree(response.getBody());
    }

    private Integer createHotel(HotelDto hotel)
            throws JSONException, IOException
    {
        HttpEntity entity = new HttpEntity<>(hotel, new HttpHeaders());

        response = restTemplate.exchange(
                this.createURLWithPort("/api/v1/hotel"),
                HttpMethod.POST, entity, String.class);

        this.assertStatus(HttpStatus.CREATED);

        return objectMapper.readTree(response.getBody()).asInt();
    }

    private Integer createBooking(BookingDto booking)
            throws JSONException, IOException
    {
        HttpEntity entity = new HttpEntity<>(booking, new HttpHeaders());

        response = restTemplate.exchange(
                this.createURLWithPort("/api/v1/booking"),
                HttpMethod.POST, entity, String.class);

        this.assertStatus(HttpStatus.CREATED);

        return objectMapper.readTree(response.getBody()).asInt();
    }

    private String createURLWithPort(String _uri)
    {
        return "http://localhost:" + port + _uri;
    }

    private void assertStatus(HttpStatus _status)
    {
        assertThat(response.getStatusCode(), equalTo(_status));
    }
}
