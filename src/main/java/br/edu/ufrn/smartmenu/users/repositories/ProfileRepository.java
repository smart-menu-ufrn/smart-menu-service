package br.edu.ufrn.smartmenu.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufrn.smartmenu.users.models.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>{
    
}
