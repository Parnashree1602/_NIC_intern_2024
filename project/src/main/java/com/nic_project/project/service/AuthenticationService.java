package com.nic_project.project.service;

import com.nic_project.project.service.AuthenticationRequest;
import com.nic_project.project.service.AuthenticationResponse;
import com.nic_project.project.service.RegisterRequest;
import com.nic_project.project.Jwt.JwtService;
import com.nic_project.project.model.Role;
import com.nic_project.project.model.Client;
import com.nic_project.project.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1);
        Date expiryDate = calendar.getTime();

        var user = Client.builder()
                .client_id(request.getClient_id())
                .client_secret(passwordEncoder.encode(request.getClient_secret()))
                .created_on(date)
                .expiry_on(expiryDate)
                .role(Role.USER)
                .build();
        clientRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getClient_id(), request.getClient_secret())
        );
        var user = clientRepository.findByClientId(request.getClient_id()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}



