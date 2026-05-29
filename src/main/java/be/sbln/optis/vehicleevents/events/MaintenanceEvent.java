package be.sbln.optis.vehicleevents.events;

import be.sbln.optis.vehicleevents.models.CarDealer;
import be.sbln.optis.vehicleevents.models.Maintenance;
import be.sbln.optis.vehicleevents.models.MaintenanceAction;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceEvent extends AbstractVehicleEvent {

    @NotBlank
    private String dealerCode;
    @NotBlank
    @Length(max = 2)
    private String dealerCountry;
    @NotNull
    private List<MaintenanceAction> maintenanceActions;
}
