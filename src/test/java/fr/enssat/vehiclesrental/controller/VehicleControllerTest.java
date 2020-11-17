package fr.enssat.vehiclesrental.controller;

import fr.enssat.vehiclesrental.controller.constants.Constants;
import fr.enssat.vehiclesrental.controller.constants.Constants.VehicleController.GetVehicles;
import fr.enssat.vehiclesrental.service.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
public class VehicleControllerTest {

    @Mock
    private WebApplicationContext wac;

    @Mock
    private VehicleService vehicleService;

    @InjectMocks
    private VehicleController vehicleController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(vehicleController).build();
    }

    @DisplayName("Get vehicles")
    @Test
    public void getVehicles() throws Exception {
        mockMvc.perform(get(GetVehicles.URL))
                .andDo(print())
                .andExpect(view().name(GetVehicles.VIEW));
    }
}
