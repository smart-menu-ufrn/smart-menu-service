package br.edu.ufrn.smartmenu.orders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufrn.smartmenu.orders.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
