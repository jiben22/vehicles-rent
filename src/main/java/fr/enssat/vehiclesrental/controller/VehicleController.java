package fr.enssat.vehiclesrental.controller;

import fr.enssat.vehiclesrental.model.Vehicle;
import fr.enssat.vehiclesrental.model.enums.VehicleType;
import fr.enssat.vehiclesrental.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import static fr.enssat.vehiclesrental.controller.constants.Constants.Controller.TITLE;
import static fr.enssat.vehiclesrental.controller.constants.Constants.VehicleController.GetVehicles;
import static fr.enssat.vehiclesrental.controller.constants.Constants.VehicleController.VEHICLES;

@RequiredArgsConstructor
@Controller
@Slf4j
public class VehicleController {

    private final VehicleService vehicleService;

    //TODO: pre authorize
    @GetMapping(GetVehicles.URL)
    public String getVehicles(Model springModel, @RequestParam Optional<String> vehicleType, @RequestParam(defaultValue = "") String model, @RequestParam(defaultValue = "") String brand, @RequestParam(defaultValue = "0") int nbSeats) {
        log.info(String.format("GET %s", GetVehicles.URL));
        springModel.addAttribute(TITLE, GetVehicles.TITLE);

        System.out.println(vehicleType.isPresent());

        List<? extends Vehicle> vehicles;
        if (vehicleType.isPresent()) {
            VehicleType type = VehicleType.valueOf(vehicleType.get());
            System.out.println(type.toString());
            switch (type) {
                case CAR:
                    System.out.println("CAR CAR CAR");
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
        System.out.println(vehicles);

        return GetVehicles.VIEW;
    }
}
