package br.edu.ufrn.smartmenu.orders.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import br.edu.ufrn.smartmenu.items.models.Item;

@Entity
public class OrderedItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private Item menuItem;


    private int quantity;



    public OrderedItem() {
    }

    public OrderedItem(Item menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public Item getMenuItem(){
        return this.menuItem;
    }

    public void setMenuItem(Item menuItem){
        this.menuItem = menuItem;
    }



}