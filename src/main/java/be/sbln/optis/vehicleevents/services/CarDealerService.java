package be.sbln.optis.vehicleevents.services;

import be.sbln.optis.vehicleevents.models.CarDealer;
import be.sbln.optis.vehicleevents.repos.CarDealerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarDealerService {

    private final CarDealerRepository repo;

    public Optional<CarDealer> findCarDealerByDealerCode(String dealerCode){
        return repo.findCarDealerByDealerCode(dealerCode);
    }

}
