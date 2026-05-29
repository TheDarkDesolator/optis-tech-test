package be.sbln.optis.vehicleevents.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceAction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String description;
    private String performedBy; //In real life this should be a user
    @ManyToOne
    @JoinColumn(name = "maintenance_id")
    private Maintenance maintenance;
}
