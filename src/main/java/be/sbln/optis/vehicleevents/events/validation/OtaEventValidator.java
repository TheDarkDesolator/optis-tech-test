package be.sbln.optis.vehicleevents.events.validation;

import be.sbln.optis.vehicleevents.events.OtaEvent;
import jakarta.validation.ValidationException;

public class OtaEventValidator implements VehicleEventValidator<OtaEvent> {

    @Override
    public Class<OtaEvent> validates() {
        return OtaEvent.class;
    }

    @Override
    public void validateEvent(OtaEvent event) throws ValidationException {
        //TODO: no domain validation needed i think
    }
}
