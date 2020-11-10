package fr.enssat.vehiclesrental.factory;

import fr.enssat.vehiclesrental.model.Car;
import fr.enssat.vehiclesrental.model.Motorbike;
import fr.enssat.vehiclesrental.model.Plane;
import fr.enssat.vehiclesrental.model.State;

public class VehicleFactory {

    public static Car getCar() {
         return Car.builder()
                 .id(42L)
                 .brand("acura")
                 .maximumSpeed(185)
                 .model("ilx")
                 .nbSeats(7)
                 .rentPricePerDay(318.2f)
                 .state(State.EXCELLENT)
                 .horsePower(201)
                 .km(150)
                 .build();
    }

    public static Motorbike getMotorbike() {
        return Motorbike.builder()
                .id(782L)
                .brand("kawasaki")
                .maximumSpeed(190)
                .model("vulcan s")
                .nbSeats(2)
                .rentPricePerDay(100.5f)
                .state(State.EXCELLENT)
                .horsePower(60)
                .km(1200)
                .build();
    }

    public static Plane getPlane() {
        return Plane.builder()
                .id(12679L)
                .brand("robin")
                .maximumSpeed(300)
                .model("dr400")
                .nbSeats(4)
                .rentPricePerDay(900)
                .state(State.GOOD)
                .nbEngines(1)
                .nbHours(1000)
                .build();
    }
}
