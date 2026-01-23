package pro.cloudyprotection.auth;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import pro.cloudyprotection.auth.dto.AuthResponse;
import pro.cloudyprotection.auth.dto.LoginRequest;
import pro.cloudyprotection.auth.dto.RegisterRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        return new AuthResponse(authService.register(request));
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return new AuthResponse(authService.login(request));
    }
}