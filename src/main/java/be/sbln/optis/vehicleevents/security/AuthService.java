package be.sbln.optis.vehicleevents.security;


import be.sbln.optis.vehicleevents.models.Role;
import be.sbln.optis.vehicleevents.models.RoleType;
import be.sbln.optis.vehicleevents.models.UserAccount;
import be.sbln.optis.vehicleevents.models.dtos.LoginRequest;
import be.sbln.optis.vehicleevents.models.dtos.RegisterRequest;
import be.sbln.optis.vehicleevents.repos.RoleRepository;
import be.sbln.optis.vehicleevents.services.UserAccountService;
import be.sbln.optis.vehicleevents.util.LoggingUtil;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserAccountService userAccountService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public String login(LoginRequest loginRequest) throws Exception{
        try {
            UserAccount user = userAccountService.findUserByUsername(loginRequest.getUsername());

            Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getUsername(), loginRequest.getPassword());
            Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);

            SecurityContextHolder.getContext().setAuthentication(authenticationResponse);

            UserDetails userDetails = (UserDetails) authenticationResponse.getPrincipal();
            return jwtUtil.generateToken(userDetails.getUsername());
        } catch (UsernameNotFoundException e) {
            throw new Exception(String.format("User with username %s does not exist!"));
        }
    }

    public UserAccount registerAccount(RegisterRequest registerRequest) throws EntityExistsException{


        try {
            userAccountService.findUserByUsername(registerRequest.getUsername());
        } catch (UsernameNotFoundException e){

            // create user object when user isn't found
            UserAccount account = new UserAccount(registerRequest.getUsername(), passwordEncoder.encode(registerRequest.getPassword()));
            Role role = roleRepository.findRoleByType(RoleType.ADMIN).orElse(null);
            account.setRoles(Collections.singleton(role));
            return userAccountService.createUserAccount(account);
        }

        throw new EntityExistsException(String.format("User with username %s already exists", registerRequest.getUsername()));


    }
}
