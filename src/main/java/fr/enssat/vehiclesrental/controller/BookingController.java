package fr.enssat.vehiclesrental.controller;

import fr.enssat.vehiclesrental.constants.ControllerConstants;
import fr.enssat.vehiclesrental.model.Booking;
import fr.enssat.vehiclesrental.model.enums.Status;
import fr.enssat.vehiclesrental.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import static fr.enssat.vehiclesrental.constants.ControllerConstants.Controller.*;
import static fr.enssat.vehiclesrental.constants.ControllerConstants.BookingController.*;

import java.util.Date;
import java.util.List;


@RequiredArgsConstructor
@Controller
@Slf4j
public class BookingController {

    /**
     * Service qui permet de gérer les réservations
     */
    private final BookingService bookingService;
    // @TODO Ne pas oublier de calculer le prix à la fin d'une location

    /**
     * Affiche la liste des réservations
     * @param springModel Modèle
     * @param startDate Date de début de la réservation
     * @param endDate Date de fin de la réservation
     * @param status Status de la réservation
     * @return La liste des réservations correspondant aux paramètres de requête
     */
    @GetMapping(ControllerConstants.BookingController.GetBookings.URL)
    public String showBookings(Model springModel,
                               @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date startDate,
                               @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date endDate,
                               @RequestParam(required = false) Status status){
        log.info(String.format("GET %s", GetBookings.URL));
        springModel.addAttribute(TITLE, GetBookings.TITLE);

        List<Booking> bookings;
        if(startDate != null || endDate != null || status != null){
            bookings=bookingService.searchBookings(startDate,endDate,status);
        }else{
            bookings = bookingService.getBookings();
        }
        springModel.addAttribute(BOOKINGS,bookings);
        return GetBookings.VIEW;
    }

    /**
     * Afficher les informations d'une réservation
     * @param springModel Modèle
     * @param id Id d'une réservation
     * @return la fiche d'une réservation
     */
    @GetMapping(GetBookingById.URL)
    public String showVehicleByRegistration(Model springModel, @PathVariable long id) {
        log.info(String.format("GET %s/%s", BASE_URL, id));
        springModel.addAttribute(TITLE, GetBookingById.TITLE);

        Booking booking = bookingService.getBooking(id);
        springModel.addAttribute(BOOKING, booking);

        return GetBookingById.VIEW;
    }
}
