package br.edu.ufrn.smartmenu.stocks.models;

import br.edu.ufrn.smartmenu.items.models.Item;
import br.edu.ufrn.smartmenu.stocks.exceptions.InsufficientItemsInStockException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "stocks")
public class Stock {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, unique = true)
    private Item item;

    @Column(nullable = false)
    private Integer quantity = 0;

    public Stock() {}

    public Stock(Item item) {
        this.item = item;
    }

    public Long getId() {
        return this.id;
    }

    public Item getItem() {
        return this.item;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void increase(Integer value) {
        this.quantity += value;
    }

    public void decrease(Integer value) throws InsufficientItemsInStockException {
        if (value > this.quantity) {
            throw new InsufficientItemsInStockException();
        }

        this.quantity -= value;
    }

}
