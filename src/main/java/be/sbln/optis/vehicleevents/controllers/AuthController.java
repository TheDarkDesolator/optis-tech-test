package be.sbln.optis.vehicleevents.controllers;

import be.sbln.optis.vehicleevents.models.UserAccount;
import be.sbln.optis.vehicleevents.models.dtos.LoginRequest;
import be.sbln.optis.vehicleevents.models.dtos.RegisterRequest;
import be.sbln.optis.vehicleevents.security.AuthService;
import be.sbln.optis.vehicleevents.security.JwtUtil;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /*@PostMapping("/token")
    public ResponseEntity<String> getToken(@RequestParam String username) {
        String token = jwtUtil.generateToken(username);
        return ResponseEntity.ok(token);
    }*/

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {

        try {
            return ResponseEntity.ok(authService.login(loginRequest));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequest request) {
        try {
            UserAccount newAccount = new UserAccount();
            newAccount.setUsername(authService.registerAccount(request).getUsername());
            return ResponseEntity.ok(newAccount);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }



}
