package be.sbln.optis.vehicleevents.events.validation;

import be.sbln.optis.vehicleevents.events.ObfcmEvent;
import be.sbln.optis.vehicleevents.models.Vehicle;
import be.sbln.optis.vehicleevents.repos.VehicleRepository;
import be.sbln.optis.vehicleevents.services.VehicleService;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ObfcmEventValidator implements VehicleEventValidator<ObfcmEvent>{

    private final VehicleService vehicleService;

    @Override
    public Class<ObfcmEvent> validates() {
        return ObfcmEvent.class;
    }

    @Override
    public void validateEvent(ObfcmEvent event) throws ValidationException {
        Optional<Vehicle> opt = vehicleService.getVehicleByVin(event.getVin());

        if(opt.isPresent()){
            Vehicle v = opt.get();

            if(event.getMileage().compareTo(v.getMileage()) < 0) throw new ValidationException("Event mileage is smaller than current mileage!");
            if(event.getTotalFuelConsumed().compareTo(v.getTotalFuelConsumed()) < 0) throw new ValidationException("Total fuel consumed is smaller than current total fuel consumed!");
        }
    }
}
