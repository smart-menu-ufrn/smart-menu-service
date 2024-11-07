package br.edu.ufrn.smartmenu.users.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ufrn.smartmenu.users.enums.Role;
import br.edu.ufrn.smartmenu.users.models.User;

@JsonInclude(Include.NON_NULL)
public class UserResponseDTO {
    
    private Long id;
    private String email;
    private ProfileResponseDTO profile;
    private Role role;

    public UserResponseDTO(
        Long id,
        String email,
        ProfileResponseDTO profile,
        Role role
    ) {
        this.id = id;
        this.email = email;
        this.profile = profile;
        this.role = role;
    }

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.profile = new ProfileResponseDTO(user.getProfile());
        this.role = user.getRole();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
    
    public ProfileResponseDTO getProfile() {
        return profile;
    }

    public Role getRole() {
        return role;
    }

}
