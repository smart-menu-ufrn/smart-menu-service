package br.edu.ufrn.smartmenu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufrn.smartmenu.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
