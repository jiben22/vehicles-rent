package fr.enssat.vehiclesrental.repository;

import fr.enssat.vehiclesrental.factory.VehicleFactory;
import fr.enssat.vehiclesrental.model.Car;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static fr.enssat.vehiclesrental.repository.CarRepository.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.data.jpa.domain.Specification.where;

@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @DisplayName("Search a car with his brand, model and nbSeats")
    @Test
    public void searchCar() {
        List<Car> cars = carRepository.findAll(where(hasBrand("acura")).and(hasModel("ilx").and(hasNbSeats(7))));
        assertEquals(1, cars.size());
        cars.forEach(car ->
                assertTrue(new ReflectionEquals(VehicleFactory.getCar(), "bookings").matches(car))
        );
    }
}
