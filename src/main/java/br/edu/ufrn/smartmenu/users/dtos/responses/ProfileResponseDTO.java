package br.edu.ufrn.smartmenu.users.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ufrn.smartmenu.users.enums.Gender;
import br.edu.ufrn.smartmenu.users.models.Profile;

@JsonInclude(Include.NON_NULL)
public class ProfileResponseDTO {
    
    private String name;
    private String phone;
    private Gender gender;

    public ProfileResponseDTO(String name, String phone, Gender gender) {
        this.name = name;
        this.phone = phone;
        this.gender = gender;
    }

    public ProfileResponseDTO(Profile profile) {
        this.name = profile.getName();
        this.phone = profile.getPhone();
        this.gender = profile.getGender();
    }

    public String getName() {
        return this.name;
    }

    public String getPhone() {
        return this.phone;
    }

    public Gender getGender() {
        return this.gender;
    }

}
