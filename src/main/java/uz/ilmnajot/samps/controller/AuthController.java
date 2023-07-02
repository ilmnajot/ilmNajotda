package uz.ilmnajot.samps.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.samps.apiResponse.ApiResponse;
import uz.ilmnajot.samps.dto.RegisterDTO;
import uz.ilmnajot.samps.dto.LoginDTO;
import uz.ilmnajot.samps.entity.User;
import uz.ilmnajot.samps.security.JwtProvider;
import uz.ilmnajot.samps.service.AuthService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    @PostMapping("/register")
    public HttpEntity<?> registerUser(@Valid @RequestBody RegisterDTO registerDTO) {
        ApiResponse apiResponse = authService.registerUser(registerDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/verifyEmail")
    public HttpEntity<?> verifyEmail(@Valid @RequestParam String username, @RequestParam String emailCode) {
        ApiResponse apiResponse = authService.verifyEmail(username, emailCode);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
     try {
         Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                 loginDTO.getUsername(),
                 loginDTO.getPassword()
         ));

User user = (User) authenticate.getPrincipal();
        String token = jwtProvider.generateToken(user.getUsername());
        return ResponseEntity.ok(token);
    }
    catch (Exception e){
         return null;
    }
    }
}
