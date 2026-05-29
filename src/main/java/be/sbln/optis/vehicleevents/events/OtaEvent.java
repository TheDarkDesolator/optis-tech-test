package be.sbln.optis.vehicleevents.events;


import be.sbln.optis.vehicleevents.models.enums.UpdateResult;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OtaEvent extends AbstractVehicleEvent{
    @NotBlank
    @Pattern(
            regexp = "^(0|[1-9]\\d*)\\.(0|[1-9]\\d*)\\.(0|[1-9]\\d*)$",
            message = "softwareVersion must follow MAJOR.MINOR.PATCH"
    )
    private String softwareVersion;
    @NotNull
    private UpdateResult result;
}
