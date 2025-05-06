package com.example.VehicleManagement.auth;

import com.example.VehicleManagement.Repositories.UserRepository;
import com.example.VehicleManagement.auth.exceptions.EmailTakenException;
import com.example.VehicleManagement.auth.exceptions.InvalidCredentialsException;
import com.example.VehicleManagement.config.JWTService;
import com.example.VehicleManagement.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public AuthResponse register(RegisterDTO dto){
        boolean emailTaken = userRepository.existsByEmail(dto.getEmail());
        if(emailTaken)
            throw new EmailTakenException("Email Taken");
        try{
            var user = new User();
            user.setFirstName(dto.getFirstName());
            user.setLastName(dto.getLastName());
            user.setEmail(dto.getEmail());
            user.setPhone(dto.getPhone());
            user.setNationalId(dto.getNationalId());
            user.setRole(dto.getRole());
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
            userRepository.save(user);
            System.out.println(user.toString());

            var token = jwtService.generateToken(user);
            return new AuthResponse(user.getFirstName(),user.getEmail(),token);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }


    public AuthResponse login(LoginDTO dto){
        System.out.println("Step 1");
        var user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow();
        var passwordsMatch = passwordEncoder.matches(dto.getPassword(), user.getPassword());
        if(!passwordsMatch)
            throw new InvalidCredentialsException("Invalid Credentials");
        String token = jwtService.generateToken(user);
        return  new AuthResponse(user.getUsername(),user.getEmail(),token);
    }

}
