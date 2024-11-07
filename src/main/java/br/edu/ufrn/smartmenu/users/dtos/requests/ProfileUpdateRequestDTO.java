package br.edu.ufrn.smartmenu.users.dtos.requests;

import br.edu.ufrn.smartmenu.users.enums.Gender;
import br.edu.ufrn.smartmenu.users.models.Profile;

public class ProfileUpdateRequestDTO {
    
    private String name;
    private String phone;
    private Gender gender;

    public ProfileUpdateRequestDTO(String name, String phone, Gender gender) {
        this.name = name;
        this.phone = phone;
        this.gender = gender;
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
    
    public Profile updateEntity(Profile profile) {

        if (this.name != null && !this.name.equals(profile.getName())) {
            profile.setName(this.name);
        }

        if (this.phone != null && !this.phone.equals(profile.getPhone())) {
            profile.setPhone(this.phone);
        }

        if (this.gender != null && !this.gender.equals(profile.getGender())) {
            profile.setGender(this.gender);
        }

        return profile;
    }

}
