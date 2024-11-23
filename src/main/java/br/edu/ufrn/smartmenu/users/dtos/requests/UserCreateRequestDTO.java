package br.edu.ufrn.smartmenu.users.dtos.requests;

import br.edu.ufrn.smartmenu.users.enums.Role;
import br.edu.ufrn.smartmenu.users.models.User;

public class UserCreateRequestDTO {
    
    private String email;
    private String password;
    private Role role;

    public UserCreateRequestDTO() {}

    public UserCreateRequestDTO(
        String email,
        String password,
        Role role
    ) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public User toEntity() {
        return new User(this.getEmail(), this.getPassword(), this.getRole());
    }
    
}
