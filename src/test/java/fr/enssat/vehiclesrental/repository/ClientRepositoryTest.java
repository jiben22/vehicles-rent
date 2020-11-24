package fr.enssat.vehiclesrental.repository;

import fr.enssat.vehiclesrental.factory.ClientFactory;
import fr.enssat.vehiclesrental.model.Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static fr.enssat.vehiclesrental.repository.ClientRepository.hasFirstname;
import static fr.enssat.vehiclesrental.repository.ClientRepository.hasZipcode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.data.jpa.domain.Specification.where;

@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @DisplayName("Search a client by his zipecode and firstname")
    @Test
    public void searchClient() {
        List<Client> clients = clientRepository.findAll(where(hasZipcode("35288").and(hasFirstname("Tim"))));
        assertEquals(1, clients.size());
        clients.forEach(client ->
                assertTrue(new ReflectionEquals(ClientFactory.getClient(), "bookings").matches(client))
        );
    }

    @DisplayName("Search a client by his zipecode and firstname")
    @Test
    public void searchUnexistingClient() {
        List<Client> clients = clientRepository.findAll(where(hasZipcode("35288").and(hasFirstname("Joe"))));
        assertEquals(0, clients.size());
    }

/*
    @DisplayName("Get motorbike with an id")
    @Test
    public void findMotorbikeById() {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(782L);
        assertTrue(optionalVehicle.isPresent());
        Vehicle vehicle = optionalVehicle.get();
        assertTrue(new ReflectionEquals(VehicleFactory.getMotorbike(), "bookings").matches(vehicle));
    }

    @DisplayName("Get plane with an id")
    @Test
    public void findPlaneById() {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(12679L);
        assertTrue(optionalVehicle.isPresent());
        Vehicle vehicle = optionalVehicle.get();
        assertTrue(new ReflectionEquals(VehicleFactory.getPlane(), "bookings").matches(vehicle));
    }

    @DisplayName("Get vehicle with an unknown id")
    @Test
    public void findByIdUnknown() {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(-999L);
        assertFalse(optionalVehicle.isPresent());
    }

    @DisplayName("Get vehicle with a brand")
    @Test
    public void findByBrand() {
        List<Vehicle> vehicles = vehicleRepository.findByBrand("acura");
        assertEquals(1, vehicles.size());
        vehicles.forEach(vehicle ->
                assertTrue(new ReflectionEquals(VehicleFactory.getCar(), "bookings").matches(vehicle))
        );
    }

    @DisplayName("Get vehicle with a model")
    @Test
    public void findByModel() {
        List<Vehicle> vehicles = vehicleRepository.findByModel("vulcan s");
        assertEquals(1, vehicles.size());
        vehicles.forEach(vehicle ->
                assertTrue(new ReflectionEquals(VehicleFactory.getMotorbike(), "bookings").matches(vehicle))
        );
    }

    @DisplayName("Get all vehicles")
    @Test
    public void findAll() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        assertEquals(13, vehicles.size());
    }

    @DisplayName("Create a new car")
    @Test
    public void saveAndFlushCar() {
        Car car = VehicleFactory.getCar();
        Car addedCar = vehicleRepository.saveAndFlush(car);
        assertTrue(new ReflectionEquals(VehicleFactory.getCar(), "bookings").matches(addedCar));
    }

    @DisplayName("Create a new motorbike")
    @Test
    public void saveAndFlushMotorbike() {
        Motorbike motorbike = VehicleFactory.getMotorbike();
        Motorbike addedMotorbike = vehicleRepository.saveAndFlush(motorbike);
        assertTrue(new ReflectionEquals(VehicleFactory.getMotorbike()).matches(addedMotorbike));
    }

    @DisplayName("Create a new plane")
    @Test
    public void saveAndFlushPlane() {
        Plane plane = VehicleFactory.getPlane();
        Plane addedPlane = vehicleRepository.saveAndFlush(plane);
        assertTrue(new ReflectionEquals(VehicleFactory.getPlane()).matches(addedPlane));
    }

    @DisplayName("Delete a car with an id")
    @Test
    public void deleteCarById() {
        vehicleRepository.deleteById(45L);
        assertFalse(vehicleRepository.existsById(45L));
    }

    @DisplayName("Delete a motorbike with an id")
    @Test
    public void deleteMotorbikeById() {
        vehicleRepository.deleteById(784L);
        assertFalse(vehicleRepository.existsById(784L));
    }

    @DisplayName("Delete a plane with an id")
    @Test
    public void deletePlaneById() {
        vehicleRepository.deleteById(12681L);
        assertFalse(vehicleRepository.existsById(12681L));
    }

    @DisplayName("Search a vehicle with his brand, model and nbSeats")
    @Test
    public void searchVehicle() {
        List<Vehicle> vehicles = vehicleRepository.findAll(where(hasBrand("acura")).and(hasModel("ilx").and(hasNbSeats(7))));
        assertEquals(1, vehicles.size());
        vehicles.forEach(vehicle ->
                assertTrue(new ReflectionEquals(VehicleFactory.getCar(), "bookings").matches(vehicle))
        );
    }*/
}
