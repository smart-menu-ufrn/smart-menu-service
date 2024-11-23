package br.edu.ufrn.smartmenu.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufrn.smartmenu.users.dtos.requests.ProfileUpdateRequestDTO;
import br.edu.ufrn.smartmenu.users.dtos.responses.ProfileResponseDTO;
import br.edu.ufrn.smartmenu.users.models.Profile;
import br.edu.ufrn.smartmenu.users.models.User;
import br.edu.ufrn.smartmenu.users.repositories.ProfileRepository;
import br.edu.ufrn.smartmenu.users.repositories.UserRepository;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    public ProfileResponseDTO updateProfile(Long id, ProfileUpdateRequestDTO profileUpdateRequestDTO) {
        User user = userRepository.findById(id).get();

        Profile profile = user.getProfile();

        Profile updatedProfile = profileUpdateRequestDTO.updateEntity(profile);

        profileRepository.save(updatedProfile);

        ProfileResponseDTO profileResponseDTO = new ProfileResponseDTO(updatedProfile);

        return profileResponseDTO;
    }

}
