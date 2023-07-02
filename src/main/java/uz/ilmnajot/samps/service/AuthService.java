package uz.ilmnajot.samps.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.ilmnajot.samps.apiResponse.ApiResponse;
import uz.ilmnajot.samps.dto.LoginDTO;
import uz.ilmnajot.samps.dto.RegisterDTO;
import uz.ilmnajot.samps.entity.User;
import uz.ilmnajot.samps.enums.RoleName;
import uz.ilmnajot.samps.repository.UserRepository;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;


    private final JavaMailSender javaMailSender;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username).orElseThrow(()
                -> new UsernameNotFoundException("username not found"));
    }

    public Boolean sendMail(String username, String emailCode) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("samarkandps@gmail.com");
            message.setTo(username);
            message.setSubject("verify the account");
            message.setText(emailCode);

            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ApiResponse registerUser(RegisterDTO registerDTO) {
        boolean username = userRepository.existsUserByUsername(registerDTO.getUsername());
        if (username) {
            return new ApiResponse("this email is already registered", false);
        }
        User user = new User(
                registerDTO.getFullName(),
                registerDTO.getUsername(),
                passwordEncoder.encode(registerDTO.getPassword()),
                RoleName.ROLE_USER
        );
        int randomNumber = new Random().nextInt(999999);
        user.setEmailCode(String.valueOf(randomNumber).substring(0, 4));
        userRepository.save(user);
        sendMail(user.getEmailCode(), user.getUsername());
        return new ApiResponse("registered successfully", true);
    }

    public ApiResponse verifyEmail(String username, String emailCode) {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (emailCode.equals(user.getEmailCode())) {
                user.setEnabled(true);
                userRepository.save(user);
                return new ApiResponse("verified successfully", true);
            }
            return new ApiResponse("email code does not match", false);
        }

        return new ApiResponse("there is no user with the given username", false);
    }


}
