package be.sbln.optis.vehicleevents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class VehicleeventsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleeventsApplication.class, args);
	}

}
