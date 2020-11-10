package fr.enssat.vehiclesrental.repository;

import fr.enssat.vehiclesrental.factory.VehicleFactory;
import fr.enssat.vehiclesrental.model.Motorbike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static fr.enssat.vehiclesrental.repository.MotorbikeRepository.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.data.jpa.domain.Specification.where;

@DataJpaTest
public class MotorbikeRepositoryTest {

    @Autowired
    private MotorbikeRepository motorbikeRepository;

    @DisplayName("Search a car with his brand, model and nbSeats")
    @Test
    public void searchMotorbike() {
        List<Motorbike> motorbikes = motorbikeRepository.findAll(where(hasBrand("kawasaki")).and(hasModel("vulcan s").and(hasNbSeats(2))));
        assertEquals(motorbikes.size(), 1);
        motorbikes.forEach(motorbike ->
                assertTrue(new ReflectionEquals(VehicleFactory.getMotorbike(), "bookings").matches(motorbike))
        );
    }
}
