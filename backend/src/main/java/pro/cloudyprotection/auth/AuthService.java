package pro.cloudyprotection.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pro.cloudyprotection.auth.dto.LoginRequest;
import pro.cloudyprotection.auth.dto.RegisterRequest;
import pro.cloudyprotection.user.User;
import pro.cloudyprotection.user.UserRepository;
import pro.cloudyprotection.user.UserRole;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalStateException("Email already registered");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setLocale(request.getLocale());
        user.setRole(UserRole.USER);

        userRepository.save(user);
        return jwtService.generateToken(user);
    }

    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                            .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return jwtService.generateToken(user);
    }
}