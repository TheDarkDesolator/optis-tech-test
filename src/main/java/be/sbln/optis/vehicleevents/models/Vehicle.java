package be.sbln.optis.vehicleevents.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    public String vin;
    public String brand;
    public String type;

    private BigDecimal mileage;
    private BigDecimal totalFuelConsumed;

    private String currentSoftwareVersion;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<Maintenance> maintenancesPerformed = new ArrayList<>();

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<VehicleSoftwareUpdate> softwareUpdates = new ArrayList<>();

}
