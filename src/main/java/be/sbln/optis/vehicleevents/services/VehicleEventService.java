package be.sbln.optis.vehicleevents.services;

import be.sbln.optis.vehicleevents.events.AbstractVehicleEvent;
import be.sbln.optis.vehicleevents.events.RawEvent;
import be.sbln.optis.vehicleevents.events.enums.VehicleEventType;
import be.sbln.optis.vehicleevents.events.validation.ValidationDispatcher;
import be.sbln.optis.vehicleevents.exceptions.VehicleEventTypeException;
import be.sbln.optis.vehicleevents.models.Vehicle;
import be.sbln.optis.vehicleevents.repos.RawEventRepository;
import be.sbln.optis.vehicleevents.util.LoggingUtil;
import be.sbln.optis.vehicleevents.util.VehicleEventUtils;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleEventService {

    private final ApplicationEventPublisher publisher;

    private final ValidationDispatcher validationDispatcher;

    private final Validator validator;

    private final RawEventRepository rawEventRepository;

    private final VehicleService vehicleService;

    private final ObjectMapper mapper;

    @Transactional
    public void processRawEvent(String rawEvent) {
        LoggingUtil.logInfo(this, "Processing raw event...", null);

        RawEvent re = new RawEvent();
        re.setContent(rawEvent.replace("\r", "")
                .replace("\n", "")
                .trim());
        re.setDeserializable(false);
        re.setReceivedAt(LocalDateTime.now());

        try {

            //Attempt to extract event type and map it if possible
            re.setEventType(VehicleEventUtils.parseEventType(rawEvent));

            //Attempt to deserialzize
            AbstractVehicleEvent event = mapper.readValue(rawEvent, AbstractVehicleEvent.class);
            re.setDeserializable(true);
            re.setVin(event.getVin());

            //CHeck if deserialized event format is valid
            Set<ConstraintViolation<AbstractVehicleEvent>> violations = validator.validate(event);

            if (!violations.isEmpty()) {
                String invalidMessage =
                        violations.stream()
                                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                                .collect(Collectors.joining(", "));
                LoggingUtil.logError(this, "Event constraints violated", invalidMessage);
                re.setInvalidMessage(invalidMessage);
            } else {

                //Validate before publishing
                validationDispatcher.validate(event);
                re.setValid(true);

                //If it's good, create the vehicle if not exists
                if (vehicleService.getVehicleByVin(event.getVin()).isEmpty()) {
                    Vehicle v = new Vehicle();
                    v.setVin(event.getVin());
                    vehicleService.createVehicle(v);
                }


                LoggingUtil.logInfo(this, "Event valid and deserializable, publishing...", null);
                publisher.publishEvent(event);


            }

        } catch (ValidationException e) {
            LoggingUtil.logError(this, "Event was deserializable but invalid!", e.getLocalizedMessage());
            re.setInvalidMessage(e.getLocalizedMessage());
        } catch (VehicleEventTypeException e) {
            LoggingUtil.logError(this, "Unknown or missing event type", e.getLocalizedMessage());
            re.setEventType(VehicleEventType.UNKNOWN);
        } catch (Exception e) {
            LoggingUtil.logError(this, "Raw event could not be processed -> " + e.getClass(), e.getLocalizedMessage());
            re.setInvalidMessage(e.getLocalizedMessage());
        } finally {
            //Persist whatever is received in its raw form
            LoggingUtil.logInfo(this, "Saving raw event...", null);
            rawEventRepository.save(re);
        }

    }


}

