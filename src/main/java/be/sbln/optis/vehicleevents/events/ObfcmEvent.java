package be.sbln.optis.vehicleevents.events;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ObfcmEvent extends AbstractVehicleEvent{

    @NotNull
    @DecimalMin("0.0")
    @Column(precision = 12, scale = 2)
    private BigDecimal mileage;
    @NotNull
    @DecimalMin("0.0")
    @Column(precision = 12, scale = 2)
    private BigDecimal totalFuelConsumed;
}
