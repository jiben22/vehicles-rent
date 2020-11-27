package fr.enssat.vehiclesrental.service;

import fr.enssat.vehiclesrental.factory.BookingFactory;
import fr.enssat.vehiclesrental.model.Booking;
import fr.enssat.vehiclesrental.model.enums.Status;
import fr.enssat.vehiclesrental.repository.BookingRepository;
import fr.enssat.vehiclesrental.service.exception.alreadyarchived.BookAlreadyArchivedException;
import fr.enssat.vehiclesrental.service.exception.alreadyrent.BookAlreadyRentException;
import fr.enssat.vehiclesrental.service.exception.inconsistentDate.StartDateAfterEndDateExceptionException;
import fr.enssat.vehiclesrental.service.exception.inconsistentDate.StartDateBeforeTodayException;
import fr.enssat.vehiclesrental.service.exception.notfound.BookingNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {
    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    @DisplayName("Get booking with an id")
    @Test
    public void getBooking(){
        when(bookingRepository.findById(anyLong()))
                .thenReturn(ofNullable(BookingFactory.getBookingAvailable()));

        Booking booking = bookingService.getBooking(148632579L);
        assertTrue(new ReflectionEquals(BookingFactory.getBookingAvailable(), new String[]{"startDate", "endDate","expectedNumberHours","client","vehicle"}).matches(booking));
    }

    @DisplayName("Get booking with an unknown id")
    @Test()
    public void getBookingUnknownIdException() {
        when(bookingRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(BookingNotFoundException.class, () -> bookingService.getBooking(-999));
    }

    @DisplayName("Get all bookings")
    @Test()
    public void getBookings(){
        List<Booking> allBookings = Arrays.asList( BookingFactory.getBookingRent(),
                BookingFactory.getBookingAvailable(),
                BookingFactory.getBookingAvailableInOctober());

        when(bookingRepository.findAll(Sort.by(Sort.Direction.DESC, "startDate")))
                .thenReturn(allBookings);

        List<Booking> bookings = bookingService.getBookings();
        AtomicInteger index = new AtomicInteger();
        bookings.forEach(booking ->
                assertTrue(new ReflectionEquals(allBookings.get(index.getAndIncrement()), new String[]{"expectedNumberHours","client","vehicle"}).matches(booking))
        );
    }

    @DisplayName("Create a new booking")
    @Test
    public void addBooking(){
        when(bookingRepository.saveAndFlush(any(Booking.class))).thenReturn(BookingFactory.getBookingAvailable());
        when(bookingRepository.existsById(anyLong())).thenReturn(false);

        Booking booking = bookingService.addBooking(BookingFactory.getBookingAvailable());
        assertTrue(new ReflectionEquals(BookingFactory.getBookingAvailable()).matches(booking));
    }

    @DisplayName("Create a new booking when booking start before today")
    @Test
    public void addBookingVehicleStartDateBeforeTodayException(){
        lenient().when(bookingRepository.saveAndFlush(any(Booking.class))).thenReturn(BookingFactory.getBookingBeforeToday());

        assertThrows(StartDateBeforeTodayException.class, () -> bookingService.addBooking(BookingFactory.getBookingBeforeToday()));
    }

    @DisplayName("Create a new booking when booking start after the end")
    @Test
    public void addBookingVehicleStartDateAfterEndDateException(){
        lenient().when(bookingRepository.saveAndFlush(any(Booking.class))).thenReturn(BookingFactory.getBookingStartAfterEnd());

        assertThrows(StartDateAfterEndDateExceptionException.class, () -> bookingService.addBooking(BookingFactory.getBookingStartAfterEnd()));
    }

    // @TODO Test when a vehicle is already book

    @DisplayName("Update a booking")
    @Test
    public void editBooking(){
        when(bookingRepository.saveAndFlush(any(Booking.class))).thenReturn(BookingFactory.getBookingAvailable());
        when(bookingRepository.findById(anyLong())).thenReturn(ofNullable(BookingFactory.getBookingAvailable()));
        when(bookingRepository.existsById(anyLong())).thenReturn(true);

        Booking booking = bookingService.editBooking(BookingFactory.getBookingAvailable());
        assertTrue(new ReflectionEquals(BookingFactory.getBookingAvailable()).matches(booking));
    }

    @DisplayName("Search bookings")
    @Test
    public void searchBookings(){
        lenient().when(bookingRepository.findAll(any(Specification.class)))
                .thenReturn(Collections.singletonList(BookingFactory.getBookingAvailable()));

        List<Booking> bookings = bookingService.searchBookings(BookingFactory.getBookingAvailable().getStartDate(), null, Status.AVAILABLE);
        assertEquals(1, bookings.size());
        bookings.forEach(booking ->
                assertTrue(new ReflectionEquals(BookingFactory.getBookingAvailable()).matches(booking))
        );
    }

    @DisplayName("Cancel a booking")
    @Test
    public void cancelBooking(){
        when(bookingRepository.findById(anyLong())).thenReturn(ofNullable(BookingFactory.getBookingAvailable()));

        bookingService.cancelBooking(148632579L);
        assertFalse(bookingService.exists(148632579L));
    }

    @DisplayName("Cancel a booking rented")
    @Test
    public void cancelBookingRentedException(){
        when(bookingRepository.findById(anyLong())).thenReturn(ofNullable(BookingFactory.getBookingRent()));

        assertThrows(BookAlreadyRentException.class, () -> bookingService.cancelBooking(148632581L));
    }

    @DisplayName("Cancel a booking archived")
    @Test
    public void cancelBookingArchivedException(){
        when(bookingRepository.findById(anyLong())).thenReturn(ofNullable(BookingFactory.getBookingArchived()));

        assertThrows(BookAlreadyArchivedException.class, () -> bookingService.cancelBooking(148632581L));
    }

    @DisplayName("Begin rent of a booking")
    @Test
    public void rentBooking(){
        when(bookingRepository.findById(anyLong())).thenReturn(ofNullable(BookingFactory.getBookingAvailable()));
        when(bookingRepository.saveAndFlush(any(Booking.class))).thenReturn(BookingFactory.getBookingRent());

        Booking booking = bookingService.rentBooking(148632579L);
        assertEquals(Status.RENTED,booking.getStatus());
    }

    @DisplayName("Archive a booking")
    @Test
    public void archiveBooking(){
        when(bookingRepository.findById(anyLong())).thenReturn(ofNullable(BookingFactory.getBookingRent()));
        when(bookingRepository.saveAndFlush(any(Booking.class))).thenReturn(BookingFactory.getBookingArchived());

        Booking booking = bookingService.archiveBooking(148632581L);
        assertEquals(Status.ARCHIVED,booking.getStatus());
    }
}
