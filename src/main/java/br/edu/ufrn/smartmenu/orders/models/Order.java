package br.edu.ufrn.smartmenu.orders.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderedItem> orderedItems = new ArrayList<>();

    public Order() {}

    public Order(List<OrderedItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public Long getId() {
        return id;
    }

    public List<OrderedItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderedItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public void addOrderedItem(OrderedItem item) {
        this.orderedItems.add(item);
    }

    public void removeOrderedItem(OrderedItem item) {
        this.orderedItems.remove(item);
    }

    public Double getPrice() {
        Double price = this.orderedItems.stream()
            .mapToDouble(item -> item.getPrice())
            .sum();

        return price;
    }

}
