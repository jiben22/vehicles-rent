package fr.enssat.vehiclesrental.controller;

import fr.enssat.vehiclesrental.model.Vehicle;
import fr.enssat.vehiclesrental.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static fr.enssat.vehiclesrental.controller.constants.Constants.Controller.TITLE;
import static fr.enssat.vehiclesrental.controller.constants.Constants.VehicleController.*;

@RequiredArgsConstructor
@Controller
@Slf4j
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping(GetVehicles.URL)
    public String getVehicles(Model model) {

        log.info(String.format("GET %s", GetVehicles.URL));
        model.addAttribute(TITLE, GetVehicles.TITLE);

        List<Vehicle> vehicles = vehicleService.getVehicles();
        model.addAttribute(VEHICLES, vehicles);

        return GetVehicles.VIEW;
    }
}
