package be.sbln.optis.vehicleevents.events.validation;

import be.sbln.optis.vehicleevents.events.AbstractVehicleEvent;
import jakarta.validation.ValidationException;

public interface VehicleEventValidator <T extends AbstractVehicleEvent> {

    Class<T> validates();

    void validateEvent(T event) throws ValidationException;
}
