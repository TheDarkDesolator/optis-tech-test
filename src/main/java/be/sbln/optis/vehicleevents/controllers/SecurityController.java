package be.sbln.optis.vehicleevents.controllers;

import be.sbln.optis.vehicleevents.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class SecurityController {

    private final JwtUtil jwtUtil;

    @PostMapping("/token")
    public ResponseEntity<String> getToken(@RequestParam String username) {
        String token = jwtUtil.generateToken(username);
        return ResponseEntity.ok(token);
    }
}
