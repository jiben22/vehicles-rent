package fr.enssat.vehiclesrental.repository;

import fr.enssat.vehiclesrental.factory.BookingFactory;
import fr.enssat.vehiclesrental.model.Booking;
import fr.enssat.vehiclesrental.model.enums.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static fr.enssat.vehiclesrental.repository.BookingRepository.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.jpa.domain.Specification.where;

@DataJpaTest
public class BookingRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;

    @DisplayName("Get a booking with an id")
    @Test
    public void findById(){
        Optional<Booking> optionalBooking = bookingRepository.findById(148632579L);
        assertTrue(optionalBooking.isPresent());
        Booking booking = optionalBooking.get();
        assertTrue(new ReflectionEquals(BookingFactory.getBookingAvailable(),new String[]{"startDate", "endDate","expectedNumberHours","client","vehicle"}).matches(booking));
    }

    @DisplayName("Get all bookings")
    @Test
    public void findAll() {
        List<Booking> bookings = bookingRepository.findAll();
        assertEquals(3, bookings.size());
    }

    @DisplayName("Create a new booking")
    @Test
    public void saveAndFlush() {
        Booking booking = BookingFactory.getBookingAvailable();
        Booking addedBooking = bookingRepository.saveAndFlush(booking);
        assertTrue(new ReflectionEquals(BookingFactory.getBookingAvailable(),new String[]{"expectedNumberHours","client","vehicle"}).matches(addedBooking));
    }

    @DisplayName("Delete a booking with an id")
    @Test
    public void deleteById() {
        bookingRepository.deleteById(148632579L);
        assertFalse(bookingRepository.existsById(148632579L));
    }

    @DisplayName("Find a booking by vehicle beetween date")
    @Test
    public void findBookingsByVehicleAndStartDateBetweenOrEndDateBetween(){
        // On test que le véhicule est déjà réservé durant la période demandée
        Booking booking = BookingFactory.getBookingRent();
        List<Booking> bookings = bookingRepository.findBookingsByVehicleAndStartDateBetweenOrEndDateBetween(booking.getVehicle(),booking.getStartDate(),booking.getEndDate(),booking.getStartDate(),booking.getEndDate());
        assertEquals(1,bookings.size());
        // On test que le véhicule n'est pas réseré durant la période demandée
        booking = BookingFactory.getBookingAvailableStart1MonthEnd2Month();
        bookings = bookingRepository.findBookingsByVehicleAndStartDateBetweenOrEndDateBetween(booking.getVehicle(),booking.getStartDate(),booking.getEndDate(),booking.getStartDate(),booking.getEndDate());
        assertEquals(0,bookings.size());
    }


    @DisplayName("Search a booking with his startDate, endDate and status")
    @Test
    public void searchBooking() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(Calendar.HOUR_OF_DAY,0);
        startDate.set(Calendar.MINUTE,0);
        startDate.set(Calendar.SECOND,0);
        LocalDate startTime = LocalDate.ofInstant(startDate.toInstant(), ZoneId.systemDefault());


        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DATE,7);
        endDate.set(Calendar.HOUR_OF_DAY,0);
        endDate.set(Calendar.MINUTE,0);
        endDate.set(Calendar.SECOND,0);
        LocalDate endTime = LocalDate.ofInstant(endDate.toInstant(), ZoneId.systemDefault());

        List<Booking> bookings = bookingRepository.findAll(where(hasStatus(Status.RENTED)).and(hasStartDate(startTime).and(hasEndDate(endTime))));
        assertEquals(1, bookings.size());
        bookings.forEach(booking ->
                assertTrue(new ReflectionEquals(BookingFactory.getBookingRent(), new String[]{"expectedNumberHours","client","vehicle"}).matches(booking))
        );
    }

}
