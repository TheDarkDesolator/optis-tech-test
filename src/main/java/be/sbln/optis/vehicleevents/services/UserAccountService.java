package be.sbln.optis.vehicleevents.services;


import be.sbln.optis.vehicleevents.models.UserAccount;
import be.sbln.optis.vehicleevents.repos.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final UserRepository userRepository;

    public UserAccount findUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User with username %s not found!", username)));
    }

    @Transactional
    public UserAccount createUserAccount(UserAccount account){
        return userRepository.saveAndFlush(account);
    }
}
