package br.edu.ufrn.smartmenu.stocks.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufrn.smartmenu.items.models.Item;
import br.edu.ufrn.smartmenu.stocks.models.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{
    
    Optional<Stock> findByItem(Item item);

}
