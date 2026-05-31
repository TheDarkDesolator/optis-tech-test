package be.sbln.optis.vehicleevents.util;

import be.sbln.optis.vehicleevents.models.CarDealer;
import be.sbln.optis.vehicleevents.models.dtos.RegisterRequest;
import be.sbln.optis.vehicleevents.repos.CarDealerRepository;
import be.sbln.optis.vehicleevents.repos.UserRepository;
import be.sbln.optis.vehicleevents.security.AuthService;
import be.sbln.optis.vehicleevents.services.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final CarDealerRepository carDealerRepository;
    private final AuthService authService;

    @Override
    public void run(String... args) {

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("admin");
        registerRequest.setPassword("admin");
        authService.registerAccount(registerRequest);

        seedCarDealers();

        // Future seeds:
        // seedVehicles();
        // seedUsers();
    }

    private void seedCarDealers() {

        if (carDealerRepository.count() > 0) {
            return;
        }

        List<CarDealer> dealers = List.of(

                new CarDealer(
                        "BE001",
                        "Brussels Auto Group",
                        "Rue de la Loi 12, Brussels",
                        "BE"
                ),

                new CarDealer(
                        "DE001",
                        "Berlin Motors",
                        "Alexanderplatz 5, Berlin",
                        "DE"
                ),

                new CarDealer(
                        "FR001",
                        "Paris Mobility Center",
                        "Champs-Élysées 88, Paris",
                        "FR"
                ),

                new CarDealer(
                        "NL001",
                        "Amsterdam Cars",
                        "Damrak 101, Amsterdam",
                        "NL"
                )
        );

        carDealerRepository.saveAll(dealers);
    }
}
