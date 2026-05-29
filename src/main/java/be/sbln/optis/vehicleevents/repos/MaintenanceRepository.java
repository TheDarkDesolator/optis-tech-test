package be.sbln.optis.vehicleevents.repos;

import be.sbln.optis.vehicleevents.models.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, UUID> {
}
