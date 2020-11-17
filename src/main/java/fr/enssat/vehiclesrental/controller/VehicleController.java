package fr.enssat.vehiclesrental.controller;

import fr.enssat.vehiclesrental.controller.constants.Constants;
import fr.enssat.vehiclesrental.controller.constants.Constants.VehicleController.GetVehicleById;
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
     * GET /véhicules
     * @param springModel Modèle
     * @param vehicleType Type du véhicule
     * @param model Modèle du véhicule
     * @param brand Marque du véhicule
     * @param nbSeats Nombre de sièges dans le véhicule
     * @return la liste des véhicules correspondant aux paramètres de requête
     */
    @GetMapping(GetVehicles.URL)
    public String getVehicles(Model springModel,
                              @RequestParam Optional<String> vehicleType,
                              @RequestParam(defaultValue = "") String model,
                              @RequestParam(defaultValue = "") String brand,
                              @RequestParam(defaultValue = "0") int nbSeats) {
        log.info(String.format("GET %s", GetVehicles.URL));
        springModel.addAttribute(TITLE, GetVehicles.TITLE);

        List<? extends Vehicle> vehicles;
        if (vehicleType.isPresent()) {
            VehicleType type = VehicleType.valueOf(vehicleType.get());
            System.out.println(type.toString());
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
                    throw new IllegalStateException("Unexpected value: " + vehicleType);
            }
        } else {
            vehicles = vehicleService.getVehicles();
        }

        springModel.addAttribute(VEHICLES, vehicles);

        return GetVehicles.VIEW;
    }

    @GetMapping(GetVehicleById.URL)
    public String getVehicleById(Model springModel, @PathVariable String id) {
        log.info(String.format("GET %s", GetVehicleById.URL));
        springModel.addAttribute(TITLE, GetVehicleById.TITLE);

        Vehicle vehicle = vehicleService.getVehicle(Long.parseLong(id));
        springModel.addAttribute(VEHICLE, vehicle);

        return GetVehicleById.VIEW;
    }

    @PreAuthorize(value = "hasAnyAuthority(T(fr.enssat.vehiclesrental.model.enums.Position).RESPONSABLE_LOCATION.label, T(fr.enssat.vehiclesrental.model.enums.Position).GESTIONNAIRE_TECHNIQUE.label)")
    @PostMapping(AddVehicle.URL)
    public String addVehicle(@Valid @ModelAttribute("vehicle") Vehicle vehicle,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {
        log.info(String.format("POST %s", AddVehicle.URL));

        // Check if form has errors
        if (result.hasErrors()) {
            log.info(result.toString());

            // Return form with errors
            return AddVehicle.VIEW;
        }

        //TODO: check if registration already exists!
        try {
            Vehicle existedVehicle = vehicleService.getVehicleByRegistration(vehicle.getRegistration());

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

                return "redirect:/vehicles";
            }
        }

        return String.format("redirect:/vehicles/%d", vehicle.getId());
    }

    @PreAuthorize(value = "hasAnyAuthority(T(fr.enssat.vehiclesrental.model.enums.Position).RESPONSABLE_LOCATION.label, T(fr.enssat.vehiclesrental.model.enums.Position).GESTIONNAIRE_TECHNIQUE.label)")
    @PutMapping(UpdateVehicle.URL)
    public String updateVehicle(@PathVariable String id, @Valid @ModelAttribute("vehicle") Vehicle vehicle, BindingResult result, Model springModel, RedirectAttributes redirectAttributes) {
        log.info(String.format("PUT %s", UpdateVehicle.URL));

        if (result.hasErrors()) {
            log.info(result.toString());

            // Return form with errors
            return UpdateVehicle.VIEW;
        }

        try {
            // Update vehicle
            if (vehicleService.exists(vehicle.getId())) {
                vehicle = vehicleService.editVehicle(vehicle);
            } else {
                throw new Exception("Le véhicule n'existe pas " + vehicle.getId());
            }
        } catch (Exception exception) {
            log.error(exception.getMessage() + exception.getCause());
            redirectAttributes.addFlashAttribute(MESSAGE, UpdateVehicle.ERROR_MESSAGE);

            return "redirect:/vehicles";
        }

        return String.format("redirect:/vehicles/%d", vehicle.getId());
    }

    @PreAuthorize(value = "hasAnyAuthority(T(fr.enssat.vehiclesrental.model.enums.Position).RESPONSABLE_LOCATION.label, T(fr.enssat.vehiclesrental.model.enums.Position).GESTIONNAIRE_TECHNIQUE.label)")
    @DeleteMapping(DeleteVehicle.URL)
    public String deleteVehicle(@PathVariable String id, @Valid @ModelAttribute("vehicle") Vehicle vehicle, BindingResult result, Model springModel, RedirectAttributes redirectAttributes) {
        log.info(String.format("DELETE %s", DeleteVehicle.URL));

        if (result.hasErrors()) {
            log.info(result.toString());

            // Return form with errors
            return DeleteVehicle.VIEW;
        }

        try {
            // Delete vehicle
            vehicleService.deleteVehicle(vehicle.getId());
        } catch (Exception exception) {
            log.error(exception.getMessage() + exception.getCause());
            redirectAttributes.addFlashAttribute(MESSAGE, DeleteVehicle.ERROR_MESSAGE);

            return "redirect:/vehicles";
        }

        return String.format("redirect:/vehicles/%d", vehicle.getId());
    }
}
