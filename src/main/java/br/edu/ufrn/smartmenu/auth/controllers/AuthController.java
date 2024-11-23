package br.edu.ufrn.smartmenu.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufrn.smartmenu.auth.dtos.requests.AuthRequestDTO;
import br.edu.ufrn.smartmenu.auth.exceptions.InvalidCredentialsException;
import br.edu.ufrn.smartmenu.auth.services.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<Void> login(@RequestBody AuthRequestDTO authRequestDTO) {
        try {
            authService.authenticate(authRequestDTO);

            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
