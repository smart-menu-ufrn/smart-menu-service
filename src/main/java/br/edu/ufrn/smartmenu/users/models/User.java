package br.edu.ufrn.smartmenu.users.models;

import org.mindrot.jbcrypt.BCrypt;

import br.edu.ufrn.smartmenu.users.enums.Role;
import br.edu.ufrn.smartmenu.users.exceptions.IncorrectPasswordException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Size(min = 8)
    @Column(nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", nullable = false, unique = true)
    private Profile profile;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public User() {}

    public User(String email, String password, Role role) {
        this.setEmail(email);
        this.setPassword(password);
        this.setProfile(new Profile());
        this.setRole(role);
    }

    public Long getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String generateEncriptedPassword(String rawPassword) {
        String encodedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt());

        return encodedPassword;
    }

    public void setPassword(String password) {
        this.password = this.generateEncriptedPassword(password);
    }

    public Profile getProfile() {
        return this.profile;
    }

    private void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void validatePassword(String passwordToValidate) throws IncorrectPasswordException {
        Boolean isValid = BCrypt.checkpw(passwordToValidate, this.password);

        if (!isValid) {
            throw new IncorrectPasswordException("password is incorrect.");
        }
    }

}
