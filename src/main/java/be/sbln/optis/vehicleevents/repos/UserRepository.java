package be.sbln.optis.vehicleevents.repos;

import be.sbln.optis.vehicleevents.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserAccount, String> {
    Optional<UserAccount> findUserByUsername(String username);

    UserAccount existsUserAccountByUsername(String username);
}
