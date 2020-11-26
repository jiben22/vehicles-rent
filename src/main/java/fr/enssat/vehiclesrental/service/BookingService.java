package fr.enssat.vehiclesrental.service;

import fr.enssat.vehiclesrental.model.Booking;
import fr.enssat.vehiclesrental.model.enums.Status;
import fr.enssat.vehiclesrental.repository.BookingRepository;
import fr.enssat.vehiclesrental.service.exception.alreadyarchived.BookAlreadyArchivedException;
import fr.enssat.vehiclesrental.service.exception.alreadybook.VehicleAlreadyBookException;
import fr.enssat.vehiclesrental.service.exception.alreadyexists.BookingAlreadyExistsException;
import fr.enssat.vehiclesrental.service.exception.alreadyrent.BookAlreadyRentException;
import fr.enssat.vehiclesrental.service.exception.inconsistentDate.StartDateAfterEndDateExceptionException;
import fr.enssat.vehiclesrental.service.exception.inconsistentDate.StartDateBeforeTodayException;
import fr.enssat.vehiclesrental.service.exception.notfound.BookingNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static fr.enssat.vehiclesrental.repository.BookingRepository.*;
import static org.springframework.data.jpa.domain.Specification.where;

/**
 * Service pour gérer les réservations
 */
@RequiredArgsConstructor
@Service
public class BookingService implements IBookingService{

    private final BookingRepository repository;

    @Override
    public boolean exists(long id) {
        return repository.existsById(id);
    }

    @Override
    public Booking getBooking(long id) {
        return repository.findById(id).orElseThrow(() -> new BookingNotFoundException(String.valueOf(id)));
    }

    @Override
    public Booking addBooking(Booking booking) {
        if(repository.existsById(booking.getId())){
            throw new BookingAlreadyExistsException(booking);
        }
        if(booking.getStartDate().isBefore(getTodayDate())){
            throw new StartDateBeforeTodayException();
        }
        if(booking.getStartDate().isAfter(booking.getEndDate())){
            throw new StartDateAfterEndDateExceptionException();
        }
        // Check if vehicle already reserved during ask period.
        List<Booking> bookingList = repository.findBookingsByVehicleAndStartDateBetweenOrEndDateBetween(booking.getVehicle(),booking.getStartDate(),booking.getEndDate(),booking.getStartDate(),booking.getEndDate());
        if(!bookingList.isEmpty()){
            for(Booking b:bookingList){
                if(b.getStatus() == Status.ARCHIVED){
                    bookingList.remove(b);
                }
            }
            if(!bookingList.isEmpty()){
                throw new VehicleAlreadyBookException(booking.getVehicle());
            }
        }
        return repository.saveAndFlush(booking);
    }

    @Override
    public Booking editBooking(Booking booking) {
        if(!repository.existsById(booking.getId())){
            throw new BookingNotFoundException(String.valueOf(booking.getId()));
        }
        if(booking.getStartDate().isBefore(getTodayDate())){
            throw new StartDateBeforeTodayException();
        }
        if(booking.getStartDate().isAfter(booking.getEndDate())){
            throw new StartDateAfterEndDateExceptionException();
        }
        // check in case of vehicle change, if new vehicle already reserved
        Optional<Booking> oldBooking = repository.findById(booking.getId());
        if(booking.getVehicle().getId() != oldBooking.get().getVehicle().getId()){
            // Check if vehicle already reserved during ask period.
            List<Booking> bookingList = repository.findBookingsByVehicleAndStartDateBetweenOrEndDateBetween(booking.getVehicle(),booking.getStartDate(),booking.getEndDate(),booking.getStartDate(),booking.getEndDate());
            if(!bookingList.isEmpty()){
                for(Booking b:bookingList){
                    if(b.getStatus() == Status.ARCHIVED){
                        bookingList.remove(b);
                    }
                }
                if(!bookingList.isEmpty()){
                    throw new VehicleAlreadyBookException(booking.getVehicle());
                }
            }
        }
        return repository.saveAndFlush(booking);
    }

    @Override
    public List<Booking> getBookings() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "startDate"));
    }

    @Override
    public List<Booking> searchBookings(LocalDate startDate, LocalDate endDate, Status status) {
        Specification<Booking> bookingSpecification = buildSpecification(startDate, endDate, status);
        if (bookingSpecification != null) {
            return repository.findAll(bookingSpecification);
        } else {
            return getBookings();
        }
    }

    private Specification<Booking> buildSpecification(LocalDate startDate, LocalDate endDate, Status status) {
        List<Specification<Booking>> specifications = new ArrayList<>();
        if (startDate != null) specifications.add(hasStartDate(startDate));
        if (endDate != null) specifications.add(hasEndDate(endDate));
        if (status != null) specifications.add(hasStatus(status));

        if (specifications.size() > 0) {
            Specification<Booking> bookingSpecification = where(specifications.get(0));
            specifications.remove(0);
            for (Specification<Booking> specification: specifications) {
                bookingSpecification = Objects.requireNonNull(bookingSpecification).and(specification);
            }

            return bookingSpecification;
        } else {
            return null;
        }
    }

    @Override
    public void cancelBooking(long id) {
        Optional<Booking> booking = repository.findById(id);

        if(booking.isEmpty()){
            throw new BookingNotFoundException(String.valueOf(id));
        }
        if(booking.get().getStatus() == Status.RENTED){
            throw new BookAlreadyRentException(booking.get());
        }

        if(booking.get().getStatus() == Status.ARCHIVED){
            throw new BookAlreadyArchivedException(booking.get());
        }
        repository.deleteById(id);
    }

    @Override
    public Booking rentBooking(long id) {
        Optional<Booking> booking = repository.findById(id);
        if(booking.isEmpty()){
            throw new BookingNotFoundException(String.valueOf(id));
        }
        if(booking.get().getStartDate().isBefore(getTodayDate())){
            throw new StartDateBeforeTodayException();
        }
        booking.get().setStatus(Status.RENTED);
        return repository.saveAndFlush(booking.get());
    }

    @Override
    public Booking archiveBooking(long id) {
        Optional<Booking> booking = repository.findById(id);
        if(booking.isEmpty()){
            throw new BookingNotFoundException(String.valueOf(id));
        }
        booking.get().setStatus(Status.ARCHIVED);
        return repository.saveAndFlush(booking.get());
    }

    private LocalDate getTodayDate(){
        Calendar todayDate = Calendar.getInstance();
        todayDate.set(Calendar.HOUR_OF_DAY,0);
        todayDate.set(Calendar.MINUTE,0);
        todayDate.set(Calendar.SECOND,0);
        LocalDate todayLocal = LocalDate.ofInstant(todayDate.toInstant(), ZoneId.systemDefault());
        return todayLocal;
    }
}
