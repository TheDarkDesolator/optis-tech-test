package be.sbln.optis.vehicleevents.repos;

import be.sbln.optis.vehicleevents.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    Optional<Vehicle> findVehicleByVin(String vin);
}
