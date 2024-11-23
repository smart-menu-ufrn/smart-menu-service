package br.edu.ufrn.smartmenu.items.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufrn.smartmenu.items.models.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
