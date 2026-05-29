package be.sbln.optis.vehicleevents.repos;

import be.sbln.optis.vehicleevents.models.VehicleSoftwareUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleSoftwareUpdateRepository extends JpaRepository<VehicleSoftwareUpdate, UUID> {
}
