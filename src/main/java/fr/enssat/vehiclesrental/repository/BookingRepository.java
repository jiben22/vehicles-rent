package fr.enssat.vehiclesrental.repository;

import fr.enssat.vehiclesrental.model.Booking;
import fr.enssat.vehiclesrental.model.Vehicle;
import fr.enssat.vehiclesrental.model.enums.Status;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> , JpaSpecificationExecutor<Booking> {
    /**
     * Récupère les réservations associées à un véhicule qui commence et s'achève dans un intervalle de dates
     *
     * @param vehicle Véhicule associé à la réservation
     * @param startDateForStart Date minimum du début de la réservation
     * @param endDateForStart Date maximum du début de la réservation
     * @param startDateForEnd Date minimum de la fin de la réservation
     * @param endDateForEnd Date maximum de la fin de la réservation
     * @return Les réservations correspondants aux critères de recherche
     */
    List<Booking> findBookingsByVehicleAndStartDateBetweenOrEndDateBetween(Vehicle vehicle, Date startDateForStart, Date endDateForStart,Date startDateForEnd, Date endDateForEnd);

    static Specification<Booking> hasStartDate(Date startDate) {
        return (booking, cq, cb) -> cb.equal(booking.get("startDate"), startDate);
    }

    static Specification<Booking> hasEndDate(Date endDate) {
        return (booking, cq, cb) -> cb.equal(booking.get("endDate"), endDate);
    }

    static Specification<Booking> hasStatus(Status status) {
        return (booking, cq, cb) -> cb.equal(booking.get("status"), status);
    }
}
