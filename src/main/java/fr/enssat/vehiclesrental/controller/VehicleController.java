package fr.enssat.vehiclesrental.controller;

import fr.enssat.vehiclesrental.controller.constants.Constants.VehicleController.*;
import fr.enssat.vehiclesrental.model.Car;
import fr.enssat.vehiclesrental.model.Motorbike;
import fr.enssat.vehiclesrental.model.Plane;
import fr.enssat.vehiclesrental.model.Vehicle;
import fr.enssat.vehiclesrental.model.enums.VehicleType;
import fr.enssat.vehiclesrental.service.VehicleService;
import fr.enssat.vehiclesrental.service.exception.notfound.VehicleNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static fr.enssat.vehiclesrental.controller.constants.Constants.Controller.MESSAGE;
import static fr.enssat.vehiclesrental.controller.constants.Constants.Controller.TITLE;
import static fr.enssat.vehiclesrental.controller.constants.Constants.VehicleController.*;

@RequiredArgsConstructor
@Controller
@Slf4j
public class VehicleController {

    private final VehicleService vehicleService;

    /**
     * Afficher la liste des véhicules
     * @param springModel Modèle
     * @param vehicleType Type du véhicule
     * @param model Modèle du véhicule
     * @param brand Marque du véhicule
     * @param nbSeats Nombre de sièges dans le véhicule
     * @return la liste des véhicules correspondant aux paramètres de requête
     */
    @GetMapping(GetVehicles.URL)
    public String showVehicles(Model springModel,
                              @RequestParam Optional<String> vehicleType,
                              @RequestParam(defaultValue = "") String model,
                              @RequestParam(defaultValue = "") String brand,
                              @RequestParam(defaultValue = "0") int nbSeats) {
        log.info(String.format("GET %s", GetVehicles.URL));
        springModel.addAttribute(TITLE, GetVehicles.TITLE);

        List<? extends Vehicle> vehicles;
        if (vehicleType.isPresent()) {
            VehicleType type = VehicleType.valueOf(vehicleType.get());
            switch (type) {
                case CAR:
                    vehicles = vehicleService.searchCars(brand, model, nbSeats);
                    break;
                case MOTORBIKE:
                    vehicles = vehicleService.searchMotorbikes(brand, model, nbSeats);
                    break;
                case PLANE:
                    vehicles = vehicleService.searchPlanes(brand, model, nbSeats);
                    break;
                default:
                    log.error(String.valueOf(new IllegalStateException("Unexpected value: " + vehicleType)));
                    vehicles = Collections.emptyList();
            }
        } else {
            vehicles = vehicleService.getVehicles();
        }

        springModel.addAttribute(VEHICLES, vehicles);

        return GetVehicles.VIEW;
    }

    /**
     * Afficher les informations d'un véhicule
     * @param springModel Modèle
     * @param registration Immatriculation du véhicule
     * @return la fiche d'un véhicule
     */
    @GetMapping(GetVehicleById.URL)
    public String getVehicleById(Model springModel, @PathVariable String registration) {
        log.info(String.format("GET %s", GetVehicleById.URL));
        springModel.addAttribute(TITLE, GetVehicleById.TITLE);

        Vehicle vehicle = vehicleService.getVehicleByRegistration(registration);
        springModel.addAttribute(VEHICLE, vehicle);

        return GetVehicleById.VIEW;
    }

    /**
     * Ajouter un véhicule
     * @param vehicle Vehicule
     * @param result X
     * @param redirectAttributes
     * @return X
     */
    @PreAuthorize(value = "hasAnyAuthority(T(fr.enssat.vehiclesrental.model.enums.Position).RESPONSABLE_LOCATION.label, T(fr.enssat.vehiclesrental.model.enums.Position).GESTIONNAIRE_TECHNIQUE.label)")
    @PostMapping(AddVehicle.URL)
    public String addVehicle(@Valid @ModelAttribute("vehicle") Vehicle vehicle,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {
        log.info(String.format("POST %s", AddVehicle.URL));

        System.out.println(vehicle);

        // Check if form has errors
        if (result.hasErrors()) {
            log.info(result.toString());

            // Return form with errors
            return AddVehicle.VIEW;
        }

        try {
            Vehicle existedVehicle = vehicleService.getVehicleByRegistration(vehicle.getRegistration());
            System.out.println(existedVehicle);
            if (existedVehicle != null) {
                result.rejectValue("registration", "vehicle.registration",
                        "Le numéro d'immatriculation est déjà attribué pour un autre véhicule");

                // Return form with errors
                return AddVehicle.VIEW;
            }
        } catch (VehicleNotFoundException vehicleNotFoundException) {
            log.info("Registration doesn't exist: %s", vehicle.getRegistration(), vehicleNotFoundException);

            try {
                // Save vehicle
                vehicle = vehicleService.addVehicle(vehicle);
            } catch (Exception exception) {
                log.error(exception.getMessage() + exception.getCause());
                redirectAttributes.addFlashAttribute(MESSAGE, AddVehicle.ERROR_MESSAGE);

                return "redirect:/vehicules";
            }
        }

        return String.format("redirect:/vehicules/%d", vehicle.getId());
    }

    @PreAuthorize(value = "hasAnyAuthority(T(fr.enssat.vehiclesrental.model.enums.Position).RESPONSABLE_LOCATION.label, T(fr.enssat.vehiclesrental.model.enums.Position).GESTIONNAIRE_TECHNIQUE.label)")
    @PutMapping(UpdateVehicle.URL)
    public String updateVehicle(@PathVariable String id,
                                @Valid @ModelAttribute("vehicle") Vehicle vehicle,
                                BindingResult result,
                                RedirectAttributes redirectAttributes) {
        log.info(String.format("PUT %s", UpdateVehicle.URL));

        if (result.hasErrors()) {
            log.info(result.toString());

            // Return form with errors
            return UpdateVehicle.VIEW;
        }

        try {
            // Update vehicle
            if (vehicleService.exists(Long.parseLong(id))) {
                vehicle = vehicleService.editVehicle(vehicle);
            } else {
                throw new Exception("Le véhicule n'existe pas " + vehicle.getId());
            }
        } catch (Exception exception) {
            log.error(exception.getMessage() + exception.getCause());
            redirectAttributes.addFlashAttribute(MESSAGE, UpdateVehicle.ERROR_MESSAGE);

            return "redirect:/vehicules";
        }

        return String.format("redirect:/vehicules/%d", vehicle.getId());
    }

//    @PreAuthorize(value = "hasAnyAuthority(T(fr.enssat.vehiclesrental.model.enums.Position).RESPONSABLE_LOCATION.label, T(fr.enssat.vehiclesrental.model.enums.Position).GESTIONNAIRE_TECHNIQUE.label)")
    @DeleteMapping(DeleteVehicle.URL)
    public void deleteVehicle(@PathVariable String id) {
        log.info(String.format("DELETE %s", DeleteVehicle.URL));

//        if (result.hasErrors()) {
//            log.info(result.toString());
//
//            // Return form with errors
//            return DeleteVehicle.VIEW;
//        }

        try {
            // Delete vehicle
            vehicleService.deleteVehicle(Long.parseLong(id));
        } catch (Exception exception) {
            log.error(exception.getMessage() + exception.getCause());
//            redirectAttributes.addFlashAttribute(MESSAGE, DeleteVehicle.ERROR_MESSAGE);
        }

//        return "redirect:/vehicules";
    }

    @PreAuthorize(value = "hasAnyAuthority(T(fr.enssat.vehiclesrental.model.enums.Position).RESPONSABLE_LOCATION.label, T(fr.enssat.vehiclesrental.model.enums.Position).GESTIONNAIRE_TECHNIQUE.label)")
    @GetMapping("/vehicules/ajouter/{vehicleType}")
    public String showAddEmployeeForm(Model model, @PathVariable String vehicleType) {

        log.info("GET /vehicules/ajouter/" + vehicleType);

        model.addAttribute("title", "Ajouter un véhicule");

        Vehicle vehicle;
        VehicleType type = VehicleType.valueOf(vehicleType);
        switch (type) {
            case CAR:
                vehicle = new Car();
                break;
            case MOTORBIKE:
                vehicle = new Motorbike();
                break;
            case PLANE:
                vehicle = new Plane();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + vehicleType);
        }
        model.addAttribute("vehicle", vehicle);

        return "addVehicleForm";
    }
}
