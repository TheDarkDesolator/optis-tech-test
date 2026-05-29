package be.sbln.optis.vehicleevents.events;


import be.sbln.optis.vehicleevents.events.enums.VehicleEventType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RawEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime receivedAt;
    private String content;
    private boolean valid;
    private boolean deserializable;
    @Column(columnDefinition = "TEXT")
    private String invalidMessage;
    private VehicleEventType eventType;
    private String vin;

    @Override
    public String toString() {
        return "RawEvent{" +
                "id=" + id +
                ", receivedAt=" + receivedAt +
                ", content='" + content + '\'' +
                ", valid=" + valid +
                ", deserializable=" + deserializable +
                ", invalidMessage='" + invalidMessage + '\'' +
                ", eventType=" + eventType +
                '}';
    }
}
