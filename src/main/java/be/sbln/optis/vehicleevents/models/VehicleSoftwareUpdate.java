package be.sbln.optis.vehicleevents.models;

import be.sbln.optis.vehicleevents.models.enums.UpdateResult;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleSoftwareUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    private Vehicle vehicle;
    private LocalDateTime updatedAt;
    private String softwareVersion;
    private UpdateResult result;
}
