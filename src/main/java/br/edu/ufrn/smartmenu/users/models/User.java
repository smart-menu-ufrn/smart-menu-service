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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(
    name = "users",
    uniqueConstraints = @UniqueConstraint(columnNames = "email")
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @NotNull
    @Size(min = 8)
    private String password;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Profile profile;

    @NotNull
    @Enumerated(EnumType.STRING)
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
