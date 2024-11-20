package br.edu.ufrn.smartmenu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufrn.smartmenu.model.OrderedItem;

@Repository
public interface OrderedItemRepository extends JpaRepository<OrderedItem, Long> {
    
    // @Query
    // ("SELECT * FROM OrderedItem where id=")
}