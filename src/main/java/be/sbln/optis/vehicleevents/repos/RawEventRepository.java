package be.sbln.optis.vehicleevents.repos;

import be.sbln.optis.vehicleevents.events.RawEvent;
import be.sbln.optis.vehicleevents.events.enums.VehicleEventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RawEventRepository extends JpaRepository<RawEvent, Long> {

    List<RawEvent> findRawEventsByVinAndEventType(String vin, VehicleEventType eventType);
    List<RawEvent> findRawEventsByVin(String vin);
}
