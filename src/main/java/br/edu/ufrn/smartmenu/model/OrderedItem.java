package br.edu.ufrn.smartmenu.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import br.edu.ufrn.smartmenu.model.MenuItem;

@Entity
public class OrderedItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private MenuItem menuItem;


    private int quantity;



    public OrderedItem() {
    }

    public OrderedItem(MenuItem menuItem, int quantity) {
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

    public MenuItem getMenuItem(){
        return this.menuItem;
    }

    public void setMenuItem(MenuItem menuItem){
        this.menuItem = menuItem;
    }



}