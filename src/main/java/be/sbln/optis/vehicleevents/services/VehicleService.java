package be.sbln.optis.vehicleevents.services;

import be.sbln.optis.vehicleevents.models.Vehicle;
import be.sbln.optis.vehicleevents.repos.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository repo;

    public Optional<Vehicle> getVehicleByVin(String vin){
        return repo.findVehicleByVin(vin);
    }

    public Vehicle createVehicle(Vehicle vehicle){
        return repo.save(vehicle);
    }

}
