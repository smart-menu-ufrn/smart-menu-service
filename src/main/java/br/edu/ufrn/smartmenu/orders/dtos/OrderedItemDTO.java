package br.edu.ufrn.smartmenu.orders.dtos;

public class OrderedItemDTO {
    private Long ItemId;
    private int quantity;


    public Long getMenuItemId(){
        return this.ItemId;
    }

    public void setMenuItemId(Long ItemId){
        this.ItemId = ItemId;
    }


    public int getQuantity(){
        return this.quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    

   
}