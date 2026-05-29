package be.sbln.optis.vehicleevents.services;

import be.sbln.optis.vehicleevents.events.RawEvent;
import be.sbln.optis.vehicleevents.events.enums.VehicleEventType;
import be.sbln.optis.vehicleevents.repos.RawEventRepository;
import jdk.jfr.EventType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RawEventService {

    private final RawEventRepository repo;


    public List<RawEvent> getEventsByVin(String vin, VehicleEventType type){
        if(type == null){
            return repo.findRawEventsByVin(vin);
        }
        return repo.findRawEventsByVinAndEventType(vin, type);
    }
}
