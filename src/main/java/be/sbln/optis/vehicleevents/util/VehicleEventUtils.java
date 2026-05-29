package be.sbln.optis.vehicleevents.util;

import be.sbln.optis.vehicleevents.events.enums.VehicleEventType;
import be.sbln.optis.vehicleevents.exceptions.VehicleEventTypeException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

public class VehicleEventUtils {

    //Attempt to parse the eventType from the rawEvent and match it to the allowed event types
    public static VehicleEventType parseEventType(String rawEvent) throws VehicleEventTypeException {
        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonNode node = mapper.readTree(rawEvent);
            JsonNode eventTypeNode = node.get("eventType");

            if (eventTypeNode == null || eventTypeNode.isNull()) {
                throw new VehicleEventTypeException("Missing eventType");
            }

            return VehicleEventType.valueOf(eventTypeNode.asText().trim().toUpperCase());

        } catch (Exception e) {
            throw new VehicleEventTypeException("Invalid eventType: " + e.getLocalizedMessage());
        }
    }

    public static VehicleEventType eventTypeFromString(String value) throws VehicleEventTypeException{
        if (value == null) return null;

        for (VehicleEventType type : VehicleEventType.values()) {
            if (type.name().equalsIgnoreCase(value.trim())) {
                return type;
            }
        }

        throw new VehicleEventTypeException("Unknown event type: " + value);
    }
}
