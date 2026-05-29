package be.sbln.optis.vehicleevents.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "maintenance",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"vehicle_vin", "maintenance_at_time"}
                )
        }
)
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "vehicle_vin")
    private Vehicle vehicle;
    private LocalDateTime maintenanceAtTime;
    @ManyToOne
    private CarDealer dealer;
    @OneToMany(mappedBy = "maintenance", cascade = CascadeType.ALL)
    private List<MaintenanceAction> maintenanceActions;

}
