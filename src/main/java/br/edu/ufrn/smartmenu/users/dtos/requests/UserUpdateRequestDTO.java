package br.edu.ufrn.smartmenu.users.dtos.requests;

import br.edu.ufrn.smartmenu.users.enums.Role;
import br.edu.ufrn.smartmenu.users.models.User;

public class UserUpdateRequestDTO {
    
    private Role role;

    public UserUpdateRequestDTO() {}

    public UserUpdateRequestDTO(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }
    
    public User updateEntity(User user) {

        if (this.role != null && !this.role.equals(user.getRole())) {
            user.setRole(this.role);
        }

        return user;
    }

}
