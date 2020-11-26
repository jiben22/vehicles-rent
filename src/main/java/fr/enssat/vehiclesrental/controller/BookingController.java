package fr.enssat.vehiclesrental.controller;

import fr.enssat.vehiclesrental.model.*;
import fr.enssat.vehiclesrental.model.enums.Status;
import fr.enssat.vehiclesrental.service.BookingService;
import fr.enssat.vehiclesrental.service.ClientService;
import fr.enssat.vehiclesrental.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import static fr.enssat.vehiclesrental.constants.ControllerConstants.BookingController.*;
import static fr.enssat.vehiclesrental.constants.ControllerConstants.ClientController.CLIENTS;
import static fr.enssat.vehiclesrental.constants.ControllerConstants.Controller.*;
import static fr.enssat.vehiclesrental.constants.ControllerConstants.VehicleController.VEHICLE;
import static fr.enssat.vehiclesrental.constants.ControllerConstants.VehicleController.VEHICLES;
import static java.time.temporal.ChronoUnit.DAYS;


@RequiredArgsConstructor
@Controller
@Slf4j
public class BookingController {
    private final float RATEFORMOTOAUTO=10f;
    private final float RATEFORPLANE=20f;
    private final float RATEFORPOWER=1.3f;
    private final float RATEPERDAY=0.1f;
    private final float RATEPERDAYPLANE=1.1f;
    /**
     * Service qui permet de gérer les réservations
     */
    private final BookingService bookingService;

    /**
     * Service qui permet de gérer les véhicules
     */
    private final VehicleService vehicleService;

    /**
     * Service qui permet de gérer les clients
     */
    private final ClientService clientService;
    // @TODO Ne pas oublier de calculer le prix à la fin d'une location

    /**
     * Affiche la liste des réservations
     * @param springModel Modèle
     * @param startDate Date de début de la réservation
     * @param endDate Date de fin de la réservation
     * @param status Status de la réservation
     * @return La liste des réservations correspondant aux paramètres de requête
     */
    @GetMapping(GetBookings.URL)
    public String showBookings(Model springModel,
                               @RequestParam(required = false) LocalDate startDate,
                               @RequestParam(required = false) LocalDate endDate,
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
    public String showBookingById(Model springModel, @PathVariable long id) {
        log.info(String.format("GET %s/%s", BASE_URL, id));
        springModel.addAttribute(TITLE, GetBookingById.TITLE);

        Booking booking = bookingService.getBooking(id);
        Vehicle vehicle = booking.getVehicle();
        //Client client = booking.getClient();

        springModel.addAttribute(BOOKING, booking);
        springModel.addAttribute(VEHICLE, vehicle);
        // @TODO Add client
        return GetBookingById.VIEW;
    }

    /**
     * Afficher le formulaire de création d'une réservation
     * @param springModel Modèle
     * @return le formulaire d'ajout
     */
    @PreAuthorize(value = "hasAnyAuthority(T(fr.enssat.vehiclesrental.model.enums.Position).RESPONSABLE_LOCATION.label, T(fr.enssat.vehiclesrental.model.enums.Position).GESTIONNAIRE_CLIENT.label)")
    @GetMapping(CreateBooking.URL)
    public String showCreateBooking(Model springModel) {
        log.info(String.format("GET %s", CreateBooking.URL));
        springModel.addAttribute(TITLE, CreateBooking.TITLE);

        springModel.addAttribute(BOOKING, new Booking());

        List<Vehicle> vehicles = vehicleService.getVehicles();
        springModel.addAttribute(VEHICLES, vehicles);

        List<Client> clients = clientService.getClients();
        springModel.addAttribute(CLIENTS, clients);

        return CreateBooking.VIEW;
    }


    /**
     * Créer une réservation
     * @param booking Réservation
     * @param result Binding result
     * @param springModel Modèle
     * @param redirectAttributes Redirect attributses
     * @return La fiche d'une réservation enregistrée ou le formulaire d'ajout avec l'erreur
     */
    @PreAuthorize(value = "hasAnyAuthority(T(fr.enssat.vehiclesrental.model.enums.Position).RESPONSABLE_LOCATION.label, T(fr.enssat.vehiclesrental.model.enums.Position).GESTIONNAIRE_CLIENT.label)")
    @PostMapping(CreateBooking.URL)
    public String createBooking(@Valid @ModelAttribute(BOOKING)  Booking booking,
                                BindingResult result,
                                Model springModel,
                                RedirectAttributes redirectAttributes) {
        log.info(String.format("Add booking %s", CreateBooking.URL));
        springModel.addAttribute(TITLE, CreateBooking.TITLE);

        //Check if form has errors
        /**if(result.hasErrors()){
            log.info(result.toString());

            return CreateBooking.VIEW;
        }*/

        // déterminer le type de véhicule selectionné
        long nbDayOfRent = DAYS.between(booking.getStartDate(), booking.getEndDate());
        float expectedPrice = 0f;
        if(booking.getVehicle() instanceof Car || booking.getVehicle() instanceof Motorbike){
            if(booking.getVehicle() instanceof Car){
                // calcul du prix pour une voiture
                Car car = (Car) booking.getVehicle();
                expectedPrice = RATEFORMOTOAUTO+car.getHorsePower()*RATEFORPOWER+(float)nbDayOfRent*RATEPERDAY;
            }else{
                // calcul du prix pour une moto
                Motorbike moto = (Motorbike) booking.getVehicle();
                expectedPrice = RATEFORMOTOAUTO+moto.getHorsePower()*RATEFORPOWER+(float)nbDayOfRent*RATEPERDAY;
            }
            booking.setExpectedNumberKm(booking.getExpectedNumberKm());
            booking.setExpectedNumberHours(null);
            // on calcul le prix correspondant au kilomètres supplémentaires parcourus
            float priceForMoreKm = 0f;
            float kmPerDay = booking.getExpectedNumberKm()/nbDayOfRent;
            if(kmPerDay > 50){
                if(kmPerDay > 50 && kmPerDay <= 100){
                    priceForMoreKm = nbDayOfRent*kmPerDay*0.5f;
                }else if(kmPerDay > 100 && kmPerDay <= 200){
                    priceForMoreKm = nbDayOfRent*kmPerDay*0.3f;
                }else if(kmPerDay > 200 && kmPerDay <= 300){
                    priceForMoreKm = nbDayOfRent*kmPerDay*0.2f;
                }else if(kmPerDay > 300){
                    priceForMoreKm = nbDayOfRent*kmPerDay*0.1f;
                }
                expectedPrice += priceForMoreKm;
            }

        }else if(booking.getVehicle() instanceof Plane){
            Plane plane = (Plane) booking.getVehicle();
            booking.setExpectedNumberHours(booking.getExpectedNumberKm());
            booking.setExpectedNumberKm(0);
            // calcul du prix pour un avion
            expectedPrice = RATEFORPLANE+ plane.getNbEngines()*RATEFORPOWER+(float)nbDayOfRent*RATEPERDAYPLANE;
            // on calcul le prix correspondant au kilomètres supplémentaires parcourus
            float priceForMoreHours = 0f;
            float hourPerDay = booking.getExpectedNumberHours()/nbDayOfRent;
            if(hourPerDay > 1.5) {
                if (hourPerDay > 1.5 && hourPerDay <= 2.5) {
                    priceForMoreHours = nbDayOfRent * hourPerDay * 0.5f;
                } else if (hourPerDay > 2.5 && hourPerDay <= 4) {
                    priceForMoreHours = nbDayOfRent * hourPerDay * 0.3f;
                } else if (hourPerDay > 4 && hourPerDay <= 6) {
                    priceForMoreHours = nbDayOfRent * hourPerDay * 0.2f;
                } else if (hourPerDay > 6) {
                    priceForMoreHours = nbDayOfRent * hourPerDay * 0.1f;
                }
                expectedPrice += priceForMoreHours;
            }
        }

        booking.setExpectedPrice(expectedPrice);
        // A la création la réservation est forcément à l'état disponible
        booking.setStatus(Status.AVAILABLE);
        // Il n'y a pas de réduction au début, on la calcul à la fin de la location
        booking.setDiscount(false);
        System.out.println(booking.getVehicle());
        bookingService.addBooking(booking);

        return String.format(REDIRECT_BOOKING_BY_ID, booking.getId());
    }

    /**
     * Annuler un réservation
     * @param id ID de la réservation
     * @param redirectAttributes Redirect attributes
     * @return la liste des réservations ou un message d'erreur si la suppression échoue
     */
    @PreAuthorize(value = "hasAnyAuthority(T(fr.enssat.vehiclesrental.model.enums.Position).RESPONSABLE_LOCATION.label,T(fr.enssat.vehiclesrental.model.enums.Position).GESTIONNAIRE_CLIENT.label)")
    @GetMapping(CancelBooking.URL)
    public String cancelBooking(@PathVariable String id,
                                 RedirectAttributes redirectAttributes) {
        log.info(String.format("GET %s", StringUtils.replace(CancelBooking.URL, PATTERN_ID, id)));

        try {
            // Delete booking
            bookingService.cancelBooking(Long.parseLong(id));
        } catch (Exception exception) {
            log.error(exception.getMessage() + exception.getCause());
            redirectAttributes.addFlashAttribute(MESSAGE, CancelBooking.ERROR_MESSAGE);
        }

        return REDIRECT_BOOKINGS;
    }
}
