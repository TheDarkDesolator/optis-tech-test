package be.sbln.optis.vehicleevents.events.listeners;

import be.sbln.optis.vehicleevents.events.AbstractVehicleEvent;

public interface VehicleEventListener<T extends AbstractVehicleEvent> {

    void handleEvent(T event);
}
