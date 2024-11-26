package br.edu.ufrn.smartmenu.orders.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.edu.ufrn.smartmenu.items.dtos.responses.ItemResponseDTO;
import br.edu.ufrn.smartmenu.orders.models.OrderedItem;

@JsonInclude(Include.NON_NULL)
public class OrderedItemResponseDTO {
    
    private ItemResponseDTO item;
    private Integer quantity;
    private Double price;

    public OrderedItemResponseDTO() {}

    public OrderedItemResponseDTO(
        ItemResponseDTO item,
        Integer quantity,
        Double price
    ) {
        this.item = item;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderedItemResponseDTO(
        OrderedItem orderedItem
    ) {
        this.item = new ItemResponseDTO(orderedItem.getItem());
        this.quantity = orderedItem.getQuantity();
        this.price = orderedItem.getPrice();
    }

    public ItemResponseDTO getItem() {
        return item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

}
