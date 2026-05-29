package be.sbln.optis.vehicleevents.events.listeners;

import be.sbln.optis.vehicleevents.events.ObfcmEvent;
import be.sbln.optis.vehicleevents.events.OtaEvent;
import be.sbln.optis.vehicleevents.models.Vehicle;
import be.sbln.optis.vehicleevents.models.VehicleSoftwareUpdate;
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
public class ObfcmEventListener implements VehicleEventListener<ObfcmEvent>{

    private final VehicleRepository repo;

    @Override
    @EventListener
    public void handleEvent(ObfcmEvent event) {

        //This is not in the assignment but I added it for realism/production minded approach
        //Could be simplified to a log message and a comment saying "Do something"

        LoggingUtil.logInfo(this, "Received an OBFCM event for car", event.getVin());
        Vehicle v = repo.findVehicleByVin(event.getVin()).get();

        v.setMileage(event.getMileage());
        v.setTotalFuelConsumed(event.getTotalFuelConsumed());

        repo.save(v);
    }
}
