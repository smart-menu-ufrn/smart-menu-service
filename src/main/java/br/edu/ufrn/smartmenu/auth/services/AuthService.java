package br.edu.ufrn.smartmenu.auth.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufrn.smartmenu.auth.components.JwtAuthUtil;
import br.edu.ufrn.smartmenu.auth.dtos.AuthRequestDTO;
import br.edu.ufrn.smartmenu.auth.dtos.AuthResponseDTO;
import br.edu.ufrn.smartmenu.auth.exceptions.InvalidCredentialsException;
import br.edu.ufrn.smartmenu.users.models.User;
import br.edu.ufrn.smartmenu.users.repositories.UserRepository;

@Service
public class AuthService {
    
    @Autowired
    private JwtAuthUtil jwtAuthUtil;

    @Autowired
    private UserRepository userRepository;

    public AuthResponseDTO authenticate(AuthRequestDTO authRequestDTO) throws InvalidCredentialsException {
        Optional<User> userOptional = userRepository.findByEmail(authRequestDTO.getEmail());
        
        if (userOptional.isEmpty()) {
            throw new InvalidCredentialsException("invalid e-mail.");
        }

        User user = userRepository.findByEmail(authRequestDTO.getEmail()).get();

        Boolean isAuthenticated = user.passwordIsValid(authRequestDTO.getPassword());

        if (!isAuthenticated) {
            throw new InvalidCredentialsException("invalid password.");
        }

        String token = jwtAuthUtil.generateToken(authRequestDTO.getEmail());

        AuthResponseDTO authResponseDTO = new AuthResponseDTO(token);

        return authResponseDTO;
    }

}
