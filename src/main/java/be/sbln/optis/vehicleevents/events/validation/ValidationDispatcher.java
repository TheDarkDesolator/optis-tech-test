package be.sbln.optis.vehicleevents.events.validation;

import be.sbln.optis.vehicleevents.events.AbstractVehicleEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This class serves as a utitlity to keep validators generic
 */
@Component
public class ValidationDispatcher {

    private final Map<Class<?>, VehicleEventValidator<?>> validators;

    public ValidationDispatcher(
            List<VehicleEventValidator<?>> validatorList
    ) {

        this.validators = validatorList.stream()
                .collect(Collectors.toMap(
                        VehicleEventValidator::validates,
                        Function.identity()
                ));
    }

    @SuppressWarnings("unchecked")
    public <T extends AbstractVehicleEvent> void validate(T event) {

        VehicleEventValidator<T> validator =
                (VehicleEventValidator<T>) validators.get(event.getClass());

        if (validator != null) {
            validator.validateEvent(event);
        }
    }
}
