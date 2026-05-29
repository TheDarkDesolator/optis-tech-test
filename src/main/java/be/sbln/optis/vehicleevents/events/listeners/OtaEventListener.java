package be.sbln.optis.vehicleevents.events.listeners;

import be.sbln.optis.vehicleevents.events.AbstractVehicleEvent;
import be.sbln.optis.vehicleevents.events.OtaEvent;
import be.sbln.optis.vehicleevents.models.Vehicle;
import be.sbln.optis.vehicleevents.models.VehicleSoftwareUpdate;
import be.sbln.optis.vehicleevents.models.enums.UpdateResult;
import be.sbln.optis.vehicleevents.repos.VehicleRepository;
import be.sbln.optis.vehicleevents.repos.VehicleSoftwareUpdateRepository;
import be.sbln.optis.vehicleevents.util.LoggingUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OtaEventListener implements VehicleEventListener<OtaEvent>{

    private final VehicleSoftwareUpdateRepository repo;
    private final VehicleRepository vehicleRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @EventListener
    public void handleEvent(OtaEvent event) {

        //This is not in the assignment but I added it for realism/production minded approach
        //Could be simplified to a log message and a comment saying "Do something"

        LoggingUtil.logInfo(this, "Received an OTA event for car", event.getVin());

        VehicleSoftwareUpdate update = new VehicleSoftwareUpdate();
        update.setUpdatedAt(event.getTimestamp());
        update.setResult(event.getResult());
        update.setSoftwareVersion(event.getSoftwareVersion());
        update.setVehicle(entityManager.getReference(Vehicle.class, event.getVin())); //This entire block should probably be objectmapped :)

        if(event.getResult().equals(UpdateResult.SUCCEEDED)){
            Vehicle vehicle = vehicleRepository.findVehicleByVin(event.getVin()).get();
            vehicle.setCurrentSoftwareVersion(event.getSoftwareVersion());
            vehicleRepository.saveAndFlush(vehicle);
        }

        repo.save(update);
    }
}
