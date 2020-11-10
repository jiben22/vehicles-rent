package fr.enssat.vehiclesrental.repository;

import fr.enssat.vehiclesrental.factory.VehicleFactory;
import fr.enssat.vehiclesrental.model.Plane;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static fr.enssat.vehiclesrental.repository.PlaneRepository.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.data.jpa.domain.Specification.where;

@DataJpaTest
public class PlaneRepositoryTest {

    @Autowired
    private PlaneRepository planeRepository;

    @DisplayName("Search a plane with his brand, model and nbSeats")
    @Test
    public void searchPlane() {
        List<Plane> planes = planeRepository.findAll(where(hasBrand("robin")).and(hasModel("dr400").and(hasNbSeats(4))));
        assertEquals(planes.size(), 1);
        planes.forEach(plane ->
                assertTrue(new ReflectionEquals(VehicleFactory.getPlane(), "bookings").matches(plane))
        );
    }
}
