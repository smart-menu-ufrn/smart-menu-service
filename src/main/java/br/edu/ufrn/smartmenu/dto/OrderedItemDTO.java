package br.edu.ufrn.smartmenu.dto;

public class OrderedItemDTO {
    private Long menuItemId;
    private int quantity;


    public Long getMenuItemId(){
        return this.menuItemId;
    }

    public void setMenuItemId(Long menuItemId){
        this.menuItemId = menuItemId;
    }


    public int getQuantity(){
        return this.quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    

   
}
