package br.edu.ufrn.smartmenu.orders.dtos.requests;

import br.edu.ufrn.smartmenu.orders.models.OrderedItem;

public class OrderedItemCreateRequestDTO {
    
    private Long itemId;
    private Integer quantity;

    public OrderedItemCreateRequestDTO() {}

    public OrderedItemCreateRequestDTO(
        Long itemId,
        Integer quantity
    ) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public Long getItemId() {
        return itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderedItem toEntity() {
        return new OrderedItem(this.getQuantity());
    }
    
}
