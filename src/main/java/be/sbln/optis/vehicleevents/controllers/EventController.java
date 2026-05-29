package be.sbln.optis.vehicleevents.controllers;


import be.sbln.optis.vehicleevents.events.AbstractVehicleEvent;
import be.sbln.optis.vehicleevents.events.RawEvent;
import be.sbln.optis.vehicleevents.exceptions.VehicleEventTypeException;
import be.sbln.optis.vehicleevents.services.RawEventService;
import be.sbln.optis.vehicleevents.services.VehicleEventService;
import be.sbln.optis.vehicleevents.util.LoggingUtil;
import be.sbln.optis.vehicleevents.util.VehicleEventUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
@Slf4j
@RequiredArgsConstructor
public class EventController {

    private final VehicleEventService vehicleEventService;
    private final RawEventService rawEventService;


    @PostMapping("")
    public ResponseEntity<Void> receiveEvents(@RequestBody String rawEvent) {


        //Validate and deserialze raw event
        //Outbox pattern would fit better but for this assignment it would be overkill (seperate scheduler for processing raw events for example).
        vehicleEventService.processRawEvent(rawEvent);

        return ResponseEntity.accepted().build();
    }

    @GetMapping("")
    public ResponseEntity retreiveEvent(@RequestParam String vin, @RequestParam(required = false) String eventType){

        try {
            List<RawEvent> events = rawEventService.getEventsByVin(vin, VehicleEventUtils.eventTypeFromString(eventType));
            return ResponseEntity.ok(events);
        } catch (VehicleEventTypeException e) {
            LoggingUtil.logError(this, e.getMessage(), e.getClass());
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
