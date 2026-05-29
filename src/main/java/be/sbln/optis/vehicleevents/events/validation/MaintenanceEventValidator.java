package be.sbln.optis.vehicleevents.events.validation;

import be.sbln.optis.vehicleevents.events.MaintenanceEvent;
import be.sbln.optis.vehicleevents.services.CarDealerService;
import be.sbln.optis.vehicleevents.util.LoggingUtil;
import com.sun.tools.javac.Main;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MaintenanceEventValidator implements VehicleEventValidator<MaintenanceEvent> {

    private final CarDealerService carDealerService;

    @Override
    public Class<MaintenanceEvent> validates() {
        return MaintenanceEvent.class;
    }

    @Override
    public void validateEvent(MaintenanceEvent event) throws ValidationException {

        if(carDealerService.findCarDealerByDealerCode(event.getDealerCode()).isEmpty()) throw new ValidationException("Dealercode in event does not refer to existing dealer -> " + event.getDealerCode());

        //TODO: Other validation could be check if country code exists but that might be overkill now
    }
}
