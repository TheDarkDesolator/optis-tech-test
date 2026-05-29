package be.sbln.optis.vehicleevents.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDealer {

    @Id
    private String dealerCode;
    private String dealerName;
    private String address;
    private String countryCode;
}
