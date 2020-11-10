package fr.enssat.vehiclesrental.service;

import fr.enssat.vehiclesrental.factory.EmployeeFactory;
import fr.enssat.vehiclesrental.factory.VehicleFactory;
import fr.enssat.vehiclesrental.model.Vehicle;
import fr.enssat.vehiclesrental.repository.CarRepository;
import fr.enssat.vehiclesrental.repository.MotorbikeRepository;
import fr.enssat.vehiclesrental.repository.PlaneRepository;
import fr.enssat.vehiclesrental.repository.VehicleRepository;
import fr.enssat.vehiclesrental.service.exception.alreadyexists.VehicleAlreadyExistException;
import fr.enssat.vehiclesrental.service.exception.notfound.VehicleNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private CarRepository carRepository;

    @Mock
    private MotorbikeRepository motorbikeRepository;

    @Mock
    private PlaneRepository planeRepository;

    @InjectMocks
    private VehicleService vehicleService;

    @DisplayName("Get vehicle with an id")
    @Test
    public void getVehicle() {
        when(vehicleRepository.findById(anyLong()))
                .thenReturn(ofNullable(VehicleFactory.getCar()));

        Vehicle vehicle = vehicleService.getVehicle(42L);
        assertTrue(new ReflectionEquals(VehicleFactory.getCar(), "bookings").matches(vehicle));
    }

    @DisplayName("Get vehicle with an unknown id")
    @Test()
    public void getVehicleException() {
        when(vehicleRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(VehicleNotFoundException.class, () -> vehicleService.getVehicle(-999));
    }

    @DisplayName("Get all vehicles")
    @Test
    public void getVehicles() {
        List<Vehicle> allVehicles = Arrays.asList(VehicleFactory.getCar(),
                VehicleFactory.getMotorbike(),
                VehicleFactory.getPlane());

        when(vehicleRepository.findAll(Sort.by(Sort.Direction.ASC, "model")))
                .thenReturn(allVehicles);

        List<Vehicle> vehicles = vehicleService.getVehicles();
        AtomicInteger index = new AtomicInteger();
        vehicles.forEach(vehicle ->
            assertTrue(new ReflectionEquals(allVehicles.get(index.getAndIncrement()), "bookings").matches(vehicle))
        );
    }

    @DisplayName("Search vehicles with parameters")
    @Test
    public void searchVehicles() {
       lenient().when(vehicleRepository.findAll(any(Specification.class)))
                .thenReturn(Collections.singletonList(VehicleFactory.getCar()));

        List<Vehicle> vehicles = vehicleService.searchVehicles("acura", "", 7);
        assertEquals(1, vehicles.size());
        vehicles.forEach(vehicle ->
                assertTrue(new ReflectionEquals(VehicleFactory.getCar(), "bookings").matches(vehicle))
        );
    }

    @DisplayName("Search vehicles with empty parameters")
    @Test
    public void searchAllVehicles() {
        lenient().when(vehicleRepository.findAll(any(Sort.class)))
                .thenReturn(Collections.singletonList(VehicleFactory.getCar()));

        List<Vehicle> vehicles = vehicleService.searchVehicles("", "", 0);
        assertEquals(1, vehicles.size());
        vehicles.forEach(vehicle ->
                assertTrue(new ReflectionEquals(VehicleFactory.getCar(), "bookings").matches(vehicle))
        );
    }

    @DisplayName("Search cars with parameters")
    @Test
    public void searchCars() {
        lenient().when(carRepository.findAll(any(Specification.class)))
                .thenReturn(Collections.singletonList(VehicleFactory.getCar()));

        List<Vehicle> vehicles = vehicleService.searchCars("acura", "", 7);
        assertEquals(1, vehicles.size());
        vehicles.forEach(vehicle ->
                assertTrue(new ReflectionEquals(VehicleFactory.getCar(), "bookings").matches(vehicle))
        );
    }

    @DisplayName("Search cars with empty parameters")
    @Test
    public void searchAllCars() {
        lenient().when(carRepository.findAll(any(Sort.class)))
                .thenReturn(Collections.singletonList(VehicleFactory.getCar()));

        List<Vehicle> vehicles = vehicleService.searchCars("", "", 0);
        assertEquals(1, vehicles.size());
        vehicles.forEach(vehicle ->
                assertTrue(new ReflectionEquals(VehicleFactory.getCar(), "bookings").matches(vehicle))
        );
    }

    @DisplayName("Search motorbikes with parameters")
    @Test
    public void searchMotorbikes() {
        lenient().when(motorbikeRepository.findAll(any(Specification.class)))
                .thenReturn(Collections.singletonList(VehicleFactory.getMotorbike()));

        List<Vehicle> vehicles = vehicleService.searchMotorbikes("acura", "", 7);
        assertEquals(1, vehicles.size());
        vehicles.forEach(vehicle ->
                assertTrue(new ReflectionEquals(VehicleFactory.getMotorbike(), "bookings").matches(vehicle))
        );
    }

    @DisplayName("Search motorbikes with empty parameters")
    @Test
    public void searchAllMotorbikes() {
        lenient().when(motorbikeRepository.findAll(any(Sort.class)))
                .thenReturn(Collections.singletonList(VehicleFactory.getMotorbike()));

        List<Vehicle> vehicles = vehicleService.searchMotorbikes("", "", 0);
        assertEquals(1, vehicles.size());
        vehicles.forEach(vehicle ->
                assertTrue(new ReflectionEquals(VehicleFactory.getMotorbike(), "bookings").matches(vehicle))
        );
    }

    @DisplayName("Search planes with parameters")
    @Test
    public void searchPlanes() {
        lenient().when(planeRepository.findAll(any(Specification.class)))
                .thenReturn(Collections.singletonList(VehicleFactory.getPlane()));

        List<Vehicle> vehicles = vehicleService.searchPlanes("acura", "", 7);
        assertEquals(1, vehicles.size());
        vehicles.forEach(vehicle ->
                assertTrue(new ReflectionEquals(VehicleFactory.getPlane(), "bookings").matches(vehicle))
        );
    }

    @DisplayName("Search planes with empty parameters")
    @Test
    public void searchAllPlanes() {
        lenient().when(planeRepository.findAll(any(Sort.class)))
                .thenReturn(Collections.singletonList(VehicleFactory.getPlane()));

        List<Vehicle> vehicles = vehicleService.searchPlanes("", "", 0);
        assertEquals(1, vehicles.size());
        vehicles.forEach(vehicle ->
                assertTrue(new ReflectionEquals(VehicleFactory.getPlane(), "bookings").matches(vehicle))
        );
    }

    @DisplayName("Create a new vehicle")
    @Test
    public void addVehicle() {
        when(vehicleRepository.saveAndFlush(any(Vehicle.class))).thenReturn(VehicleFactory.getCar());
        when(vehicleRepository.existsById(anyLong())).thenReturn(false);

        Vehicle vehicle = vehicleService.addVehicle(VehicleFactory.getCar());
        assertTrue(new ReflectionEquals(VehicleFactory.getCar(), "bookings").matches(vehicle));
    }

    @DisplayName("Create a vehicle who already exists")
    @Test
    public void addVehicleAlreadyExists() {
        when(vehicleRepository.existsById(anyLong())).thenReturn(true);
        assertThrows(VehicleAlreadyExistException.class, () -> vehicleService.addVehicle(VehicleFactory.getCar()));
    }

    @DisplayName("Update a vehicle")
    @Test
    public void editVehicle() {
        when(vehicleRepository.saveAndFlush(any(Vehicle.class))).thenReturn(VehicleFactory.getCar());
        when(vehicleRepository.existsById(anyLong())).thenReturn(true);

        Vehicle vehicle = vehicleService.editVehicle(VehicleFactory.getCar());
        assertTrue(new ReflectionEquals(VehicleFactory.getCar(), "bookings").matches(vehicle));
    }

    @DisplayName("Update an unknown vehicle")
    @Test
    public void editVehicleException() {
        when(vehicleRepository.existsById(anyLong())).thenReturn(false);
        assertThrows(VehicleNotFoundException.class, () -> vehicleService.editVehicle(VehicleFactory.getCar()));
    }

    @DisplayName("Delete a vehicle")
    @Test
    public void deleteVehicle() {
        vehicleService.deleteVehicle(784);
        assertFalse(vehicleService.exists(784));
    }
}
