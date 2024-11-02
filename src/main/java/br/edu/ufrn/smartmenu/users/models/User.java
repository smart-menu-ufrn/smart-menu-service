package br.edu.ufrn.smartmenu.users.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    public User() {
    }

    public User(String email, String password) {
        this.setEmail(email);

        this.setPassword(password);

        this.setProfile(new Profile());
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

    public void setPassword(String password) {
        this.password = this.generateEncriptedPassword(password);
    }

    private String generateEncriptedPassword(String rawPassword) {
        String encodedPassword = new BCryptPasswordEncoder().encode(rawPassword);
        
        return encodedPassword;
    }

    public boolean passwordIsValid(String passwordToValidate) {
        return new BCryptPasswordEncoder().matches(passwordToValidate, this.password);
    }

    public Profile getProfile() {
        return this.profile;
    }

    private void setProfile(Profile profile) {
        this.profile = profile;
    }

}