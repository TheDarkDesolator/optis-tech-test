package be.sbln.optis.vehicleevents;

import be.sbln.optis.vehicleevents.events.MaintenanceEvent;
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
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;


    private String maintenanceEvent = "{\n" +
            "            \"eventType\": \"MAINTENANCE\",\n" +
            "            \"vin\": \"VIN123\",\n" +
            "            \"dealerCode\": \"BE01\",\n" +
            "            \"dealerCountry\": \"BE\",\n" +
            "            \"timestamp\": \"" + LocalDateTime.now() +"\"\n" +
            "        }";
    @Test
    void shouldReturnEventAccepted() throws Exception{



        mockMvc.perform(
                post("/api/events/")
                        .with(csrf())
                        .with(user("test"))
                        .content(maintenanceEvent)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.vin").value("VIN123"))
                .andExpect(jsonPath("$.dealerCode").value("BE01"))
                .andExpect(jsonPath("$.dealerCountry").value("BE"))

        ;
    }

    @Test
    void vehicleExists(){

    }

}
