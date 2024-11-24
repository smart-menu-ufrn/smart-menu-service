package br.edu.ufrn.smartmenu.users.controllers;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufrn.smartmenu.users.dtos.requests.UserCreateRequestDTO;
import br.edu.ufrn.smartmenu.users.dtos.requests.UserUpdateRequestDTO;
import br.edu.ufrn.smartmenu.users.dtos.responses.UserResponseDTO;
import br.edu.ufrn.smartmenu.users.services.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> responseDTOList = userService.getAllUsers();

        return ResponseEntity.status(HttpStatus.OK).body(responseDTOList);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(
        @RequestBody UserCreateRequestDTO requestDTO
    ) {
        UserResponseDTO responseDTO = userService.createUser(requestDTO);

        URI location = URI.create("/users/" + responseDTO.getId());

        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(
        @PathVariable Long id
    ) {
        try {
            UserResponseDTO responseDTO = userService.getUserById(id);
            
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
        @PathVariable Long id,
        @RequestBody UserUpdateRequestDTO requestDTO
    ) {
        try {
            UserResponseDTO responseDTO = userService.updateUser(
                id,
                requestDTO
            );

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
