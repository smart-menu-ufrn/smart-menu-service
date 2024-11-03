package br.edu.ufrn.smartmenu.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufrn.smartmenu.users.dtos.ProfileResponseDTO;
import br.edu.ufrn.smartmenu.users.dtos.ProfileUpdateRequestDTO;
import br.edu.ufrn.smartmenu.users.services.ProfileService;

@RestController
@RequestMapping("/users/{id}/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PutMapping
    public ResponseEntity<ProfileResponseDTO> updateProfile(
        @PathVariable Long id,
        @RequestBody ProfileUpdateRequestDTO profileUpdateRequestDTO
    ) {
        ProfileResponseDTO profileResponseDTO = profileService.updateProfile(
            id,
            profileUpdateRequestDTO
        );

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(profileResponseDTO);
    }

}
