package br.edu.ufrn.smartmenu.users.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ufrn.smartmenu.users.models.User;

@JsonInclude(Include.NON_NULL)
public class UserResponseDTO {
    
    private Long id;
    private String email;
    private ProfileResponseDTO profile;

    public UserResponseDTO(Long id, String email, ProfileResponseDTO profile) {
        this.id = id;
        this.email = email;
        this.profile = profile;
    }

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.profile = new ProfileResponseDTO(user.getProfile());
    }

    public Long getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }
    
    public ProfileResponseDTO getProfile() {
        return this.profile;
    }

}
