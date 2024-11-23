package br.edu.ufrn.smartmenu.users.controllers;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufrn.smartmenu.users.dtos.requests.ProfileUpdateRequestDTO;
import br.edu.ufrn.smartmenu.users.dtos.responses.ProfileResponseDTO;
import br.edu.ufrn.smartmenu.users.services.ProfileService;

@RestController
@RequestMapping("/users/{id}/profile")
@CrossOrigin(origins = "http://localhost:3000")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PutMapping
    public ResponseEntity<ProfileResponseDTO> updateProfile(
        @PathVariable Long id,
        @RequestBody ProfileUpdateRequestDTO profileUpdateRequestDTO
    ) {
        try {
            ProfileResponseDTO profileResponseDTO = profileService.updateProfile(
                id,
                profileUpdateRequestDTO
            );

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(profileResponseDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
