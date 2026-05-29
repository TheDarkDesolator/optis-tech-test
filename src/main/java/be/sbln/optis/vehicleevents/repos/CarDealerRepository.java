package be.sbln.optis.vehicleevents.repos;

import be.sbln.optis.vehicleevents.models.CarDealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarDealerRepository extends JpaRepository<CarDealer, String> {

    Optional<CarDealer> findCarDealerByDealerCode(String dealerCode);

}
