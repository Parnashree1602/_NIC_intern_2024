package com.nic_project.project.controller;

import com.nic_project.project.service.AuthenticationRequest;
import com.nic_project.project.service.AuthenticationResponse;
import com.nic_project.project.service.AuthenticationService;
import com.nic_project.project.repository.UserRepository;
import com.nic_project.project.service.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService service;

    private final UserRepository userRepository;

    @PostMapping("/init")
    public ResponseEntity<AuthenticationResponse> init(@RequestBody AuthenticationRequest request){
        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            try {
                AuthenticationResponse response = service.authenticate(request);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }else{
            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.setUsername(request.getUsername());
            registerRequest.setPassword(request.getPassword());

            try {
                AuthenticationResponse response = service.register(registerRequest);
                return new ResponseEntity<>(response , HttpStatus.CREATED);
            }catch (Exception e){
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }
    }
}