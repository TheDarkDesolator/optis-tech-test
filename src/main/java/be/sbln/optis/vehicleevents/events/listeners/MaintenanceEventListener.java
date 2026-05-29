package be.sbln.optis.vehicleevents.events.listeners;

import be.sbln.optis.vehicleevents.events.MaintenanceEvent;
import be.sbln.optis.vehicleevents.events.validation.MaintenanceEventValidator;
import be.sbln.optis.vehicleevents.models.Maintenance;
import be.sbln.optis.vehicleevents.models.MaintenanceAction;
import be.sbln.optis.vehicleevents.models.Vehicle;
import be.sbln.optis.vehicleevents.repos.CarDealerRepository;
import be.sbln.optis.vehicleevents.repos.MaintenanceRepository;
import be.sbln.optis.vehicleevents.util.LoggingUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class MaintenanceEventListener implements VehicleEventListener<MaintenanceEvent> {

    @PersistenceContext
    private EntityManager entityManager;

    private final MaintenanceRepository repo;
    private final CarDealerRepository dealerRepo;

    @Override
    @Async
    @EventListener
    public void handleEvent(MaintenanceEvent event) {

        //This is not in the assignment but I added it for realism/production minded approach
        //Could be simplified to a log message and a comment saying "Do something"

        LoggingUtil.logInfo(this, "Received a maintenance event for car", event.getVin());

        Maintenance maintenance = new Maintenance();
        maintenance.setMaintenanceActions(event.getMaintenanceActions());
        maintenance.setMaintenanceAtTime(event.getTimestamp());
        maintenance.setDealer(dealerRepo.findCarDealerByDealerCode(event.getDealerCode()).get()); //This entire block should probably be objectmapped :)

        maintenance.setVehicle(entityManager.getReference(Vehicle.class, event.getVin()));

        for (MaintenanceAction a : event.getMaintenanceActions()){
            a.setMaintenance(maintenance);
        }
        maintenance.setMaintenanceActions(event.getMaintenanceActions());

        try {
            repo.saveAndFlush(maintenance);
        } catch (DataIntegrityViolationException e) {

            LoggingUtil.logError(
                    this,
                    "Already a maintenance performed for this car on this timestamp",
                    event.getVin() + " " + event.getTimestamp()
            );


        }

    }
}
