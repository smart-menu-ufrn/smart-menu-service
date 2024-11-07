package br.edu.ufrn.smartmenu.users.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufrn.smartmenu.users.dtos.requests.UserCreateRequestDTO;
import br.edu.ufrn.smartmenu.users.dtos.requests.UserUpdateRequestDTO;
import br.edu.ufrn.smartmenu.users.dtos.responses.UserResponseDTO;
import br.edu.ufrn.smartmenu.users.models.User;
import br.edu.ufrn.smartmenu.users.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public List<UserResponseDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();

        List<UserResponseDTO> userResponseDTOList = userList
            .stream()
            .map(UserResponseDTO::new)
            .collect(Collectors.toList());

        return userResponseDTOList;
    }

    public UserResponseDTO getUserById(Long id) {

        User user = userRepository.findById(id).get();

        UserResponseDTO userResponseDTO = new UserResponseDTO(user);

        return userResponseDTO;
    }

    public UserResponseDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).get();

        UserResponseDTO userResponseDTO = new UserResponseDTO(user);

        return userResponseDTO;
    }

    public UserResponseDTO createUser(UserCreateRequestDTO userCreateRequestDTO) {
        User user = userCreateRequestDTO.toUser();

        user = userRepository.save(user);

        UserResponseDTO userResponseDTO = new UserResponseDTO(user);

        return userResponseDTO;
    }

    public UserResponseDTO updateUser(Long id, UserUpdateRequestDTO userUpdateRequestDTO) {
        User user = userRepository.findById(id).get();

        User updatedUser = userUpdateRequestDTO.updateEntity(user);

        userRepository.save(updatedUser);

        UserResponseDTO userResponseDTO = new UserResponseDTO(updatedUser);

        return userResponseDTO;
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).get();
        
        userRepository.delete(user);
    }

}
