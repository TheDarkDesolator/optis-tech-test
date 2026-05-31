package be.sbln.optis.vehicleevents;

import be.sbln.optis.vehicleevents.events.MaintenanceEvent;
import be.sbln.optis.vehicleevents.models.CarDealer;
import be.sbln.optis.vehicleevents.models.dtos.RegisterRequest;
import be.sbln.optis.vehicleevents.repos.CarDealerRepository;
import be.sbln.optis.vehicleevents.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
@RequiredArgsConstructor
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final AuthService authService;
    private final CarDealerRepository dealerRepository;


    private String maintenanceEventValid = "{\n" +
            "  \"eventType\": \"MAINTENANCE\",\n" +
            "  \"vin\": \"VIN123\",\n" +
            "  \"dealerCode\": \"BE001\",\n" +
            "  \"dealerCountry\": \"BE\",\n" +
            "  \"timestamp\": \"2026-05-27T22:26:00\",\n" +
            "  \"maintenanceActions\" : [{\n" +
            "        \"description\": \"Replaced front left tire\",\n" +
            "        \"performedBy\": \"Den Johnny\"\n" +
            "  }]\n" +
            "}";

    private String unknownRequest = "{\"eventType\":\"???\",\"vin\":\"@@@\",\"timestamp\":\"2026-99-99T99:99:99\",\"mileage\":\"-NaN\",\"totalFuelConsumed\":null}";

    private String otaEventValid = "{\n" +
            "  \"eventType\": \"OTA\",\n" +
            "  \"vin\": \"VIN123\",\n" +
            "  \"result\": \"SUCCEEDED\",\n" +
            "  \"softwareVersion\": \"123.123.123\",\n" +
            "  \"timestamp\": \"2026-05-27T22:27:00\"\n" +
            "\n" +
            "}";

    private String obfcmEventValid = "{\n" +
            "  \"eventType\": \"OBFCM\",\n" +
            "  \"vin\": \"VIN123\",\n" +
            "  \"mileage\": 10.0,\n" +
            "  \"totalFuelConsumed\": 150.0,\n" +
            "  \"timestamp\": \"2026-05-27T22:27:00\"\n" +
            "}";



    @BeforeEach()
    void setup(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("admin");
        registerRequest.setPassword("admin");
        authService.registerAccount(registerRequest);

        CarDealer d = new CarDealer(
                "BE001",
                "Brussels Auto Group",
                "Rue de la Loi 12, Brussels",
                "BE"
        );

        dealerRepository.save(d);


    }

    @Test
    void shouldReturnEventAccepted() throws Exception {

        mockMvc.perform(
                        post("/api/events/")
                                .with(csrf())
                                .with(user("test"))
                                .content(maintenanceEventValid)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.vin").value("VIN123"))
                .andExpect(jsonPath("$.dealerCode").value("BE01"))
                .andExpect(jsonPath("$.dealerCountry").value("BE"))

        ;
    }

    @Test
    void vehicleExists() {

    }

}
