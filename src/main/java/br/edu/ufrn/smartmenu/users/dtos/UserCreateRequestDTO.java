package br.edu.ufrn.smartmenu.users.dtos;

import br.edu.ufrn.smartmenu.users.models.User;

public class UserCreateRequestDTO {
    
    private String email;
    private String password;

    public UserCreateRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }

    public User toUser() {
        return new User(this.getEmail(), this.getPassword());
    }
    
}
