package br.edu.ufrn.smartmenu.auth.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufrn.smartmenu.auth.dtos.requests.AuthRequestDTO;
import br.edu.ufrn.smartmenu.auth.exceptions.InvalidCredentialsException;
import br.edu.ufrn.smartmenu.users.exceptions.IncorrectPasswordException;
import br.edu.ufrn.smartmenu.users.models.User;
import br.edu.ufrn.smartmenu.users.repositories.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public void authenticate(AuthRequestDTO authRequestDTO) throws InvalidCredentialsException {
        Optional<User> userOptional = userRepository.findByEmail(authRequestDTO.getEmail());
        
        if (userOptional.isEmpty()) {
            throw new InvalidCredentialsException("invalid e-mail.");
        }

        User user = userRepository.findByEmail(authRequestDTO.getEmail()).get();

        try {
            user.validatePassword(authRequestDTO.getPassword());
        } catch (IncorrectPasswordException e) {
            throw new InvalidCredentialsException("invalid password.");
        }
    }

}
