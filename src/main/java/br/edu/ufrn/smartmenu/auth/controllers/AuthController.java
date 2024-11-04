package br.edu.ufrn.smartmenu.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufrn.smartmenu.auth.dtos.AuthRequestDTO;
import br.edu.ufrn.smartmenu.auth.dtos.AuthResponseDTO;
import br.edu.ufrn.smartmenu.auth.exceptions.InvalidCredentialsException;
import br.edu.ufrn.smartmenu.auth.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO authRequestDTO) {
        try {
            AuthResponseDTO authResponseDTO = authService.authenticate(authRequestDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(authResponseDTO);
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
