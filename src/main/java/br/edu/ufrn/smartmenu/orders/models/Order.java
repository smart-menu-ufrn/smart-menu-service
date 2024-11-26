package br.edu.ufrn.smartmenu.orders.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import br.edu.ufrn.smartmenu.orders.models.OrderedItem;

import java.util.List;

@Entity
public class Order{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<OrderedItem> items;



    public Order() {
    }

    public Order(List<OrderedItem> items) {
        this.items = items;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderedItem> getOrderedItem(){
        return this.items;
    }

    public void setMenuItem(List<OrderedItem> items){
        this.items = items;
    }



}