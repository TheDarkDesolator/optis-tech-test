package be.sbln.optis.vehicleevents.events;

import be.sbln.optis.vehicleevents.models.Vehicle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


@JsonTypeInfo(
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "eventType",
        use = JsonTypeInfo.Id.NAME
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OtaEvent.class, name = "OTA"),
        @JsonSubTypes.Type(value = MaintenanceEvent.class, name = "MAINTENANCE"),
        @JsonSubTypes.Type(value = ObfcmEvent.class, name = "OBFCM")
})
@JsonIgnoreProperties(ignoreUnknown = true)

@Getter
@Setter
public class AbstractVehicleEvent {

    @NotBlank
    private String vin;
    @NotNull
    @PastOrPresent
    private LocalDateTime timestamp;

    @Nullable
    private Boolean valid;
}
