package be.sbln.optis.vehicleevents.util;

import be.sbln.optis.vehicleevents.models.CarDealer;
import be.sbln.optis.vehicleevents.repos.CarDealerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final CarDealerRepository carDealerRepository;

    @Override
    public void run(String... args) {

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
